package My.FrontEnd;

import My.AST.Location;
import My.AST.Statement.*;
import My.Entity.*;
import My.Type.*;
import My.Utility.SemanticError;

import java.util.List;
import java.util.LinkedList;

import static My.Type.Type.*;

public class AST {
	private Scope scope;
	private List<DefinitionStatement> defNodes;
    private List<ClassEntity> classEntities;
    private List<FunctionEntity> functionEntities;
    private List<VariableEntity> variableEntities;

	public AST(List<DefinitionStatement> defNodes, List<ClassEntity> definedClasses,
			   List<FunctionEntity> definedFunctions, List<VariableEntity> definedVariables) {
		this.defNodes = defNodes;
		this.classEntities = definedClasses;
		this.functionEntities = definedFunctions;
		this.variableEntities = definedVariables;
		this.scope = new Scope(true);
	}

	public Scope scope() {
        return scope;
    }

    public List<DefinitionStatement> defNodes() {
        return defNodes;
    }

    public List<ClassEntity> classEntitsies() {
        return classEntities;
    }

    public List<FunctionEntity> functionEntities() {
        return functionEntities;
    }

    public List<VariableEntity> variableEntities() {
        return variableEntities;
    }

	//semantic check
	public void resolveSymbol() {
		for (ClassEntity entity : classEntities)
			scope.insert(entity);
		for (FunctionEntity entity : functionEntities)
			scope.insert(entity);

		Location loc = new Location(0, 0);
		scope.insert(new VariableEntity(loc, nullType, "null", null));

		List<ParameterEntity> param = new LinkedList<>();
		scope.insert(new FunctionEntity(loc, stringType, "getString", param, null));
		scope.insert(new FunctionEntity(loc, integerType, "getInt", param, null));
		param = new LinkedList<>();
		param.add(new ParameterEntity(loc, stringType, "str"));
		scope.insert(new FunctionEntity(loc, voidType, "print", param, null));
		scope.insert(new FunctionEntity(loc, voidType, "println", param, null));
		param = new LinkedList<>();
		param.add(new ParameterEntity(loc, integerType, "i"));
		scope.insert(new FunctionEntity(loc, stringType, "toString", param, null));

		StringType.initBuiltinFunction();
		ArrayType.initBuiltinFunction();

		SymbolResolver resolver = new SymbolResolver(scope);
		resolver.visitDefinitions(defNodes);
	}

	public void checkType() {
		TypeChecker checker = new TypeChecker(scope);
		checker.visitDefinitions(defNodes);

		FunctionEntity mainFunc = (FunctionEntity) scope.lookup("main");
		if (mainFunc == null)
			throw new SemanticError(new Location(0, 0), "do not find main function");
		if (!mainFunc.returnType().isInteger())
			throw new SemanticError(new Location(0, 0), "main function return type error");
	}
}
