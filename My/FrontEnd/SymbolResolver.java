package My.FrontEnd;

import My.AST.Expression.*;
import My.AST.Statement.*;
import My.AST.*;
import My.Entity.*;
import My.Type.*;
import My.Utility.SemanticError;

import java.util.Stack;

public class SymbolResolver extends Visitor {
	private Stack<Scope> stack = new Stack<>();
	private Scope currentScope;
	private Scope toplevelScope;
	private ClassEntity currentClass = null;
    private ParameterEntity currentThis = null;
    private boolean firstBlockInFunction = false;

	public SymbolResolver(Scope toplevelScope) {
		this.toplevelScope = toplevelScope;
		currentScope = toplevelScope;
		stack.push(currentScope);
	}

	private void enterScope() {
		currentScope = new Scope(currentScope);
		stack.push(currentScope);
	}
	private void exitScope() {
		stack.pop();
		currentScope = stack.peek();
	}

	private void enterClass(ClassEntity entity) {
		currentClass = entity;
		enterScope();
		entity.setScope(currentScope);
	}
	private void exitClass() {
		exitScope();
		currentClass = null;
	}

	// set entity for type
    private boolean resolveType(Type type) {
        if (type instanceof ClassType) {
            ClassType t = (ClassType)type;
            Entity entity = currentScope.lookup(t.name());
            if (entity == null || !(entity instanceof ClassEntity))
                return false;
            t.setEntity((ClassEntity)entity);
        } else if (type instanceof FunctionType) {
            FunctionType t = (FunctionType)type;
            Entity entity = currentScope.lookup(t.name());
            if (entity == null || !(entity instanceof FunctionEntity))
                return false;
            t.setEntity((FunctionEntity)entity);
        } else if (type instanceof ArrayType) {
            ArrayType t = (ArrayType)type;
            return resolveType(t.deepType());
        }
        return true;
    }

	@Override
	public Void visit(ClassDefinition node) {
		ClassEntity entity = node.entity();
		enterClass(entity);

		for (VariableDefinition var : entity.memberVars())
			currentScope.insert(var.entity());
		for (FunctionDefinition func : entity.memberFuncs())
			currentScope.insert(func.entity());

		visitStatements(entity.memberVars());
		visitStatements(entity.memberFuncs());
		exitClass();
		return null;
	}

	@Override
	public Void visit(VariableDefinition node) {
		VariableEntity entity = node.entity();
		if (!resolveType(entity.type()))
			throw new SemanticError(node.location(), "Cannot resolve symbol : " + entity.type());
		if (currentClass == null || currentClass.scope() != currentScope) { // not in Class
			if (entity.init() != null)
				visitExpression(entity.init());
			currentScope.insert(entity);
		}
		return null;
	}

	@Override
	public Void visit(FunctionDefinition node) {
		FunctionEntity entity = node.entity();
		enterScope();
		entity.setScope(currentScope);
		firstBlockInFunction = true;

		if (!resolveType(entity.returnType()))
			throw new SemanticError(node.location(), "Cannot resolve symbol : " + entity.returnType());

		if (currentClass != null)
			currentThis = entity.addThisPointer(node.location(), currentClass);

		for (ParameterEntity param : entity.params()) {
			currentScope.insert(param);
			if (!resolveType(param.type()))
				throw new SemanticError(node.location(), "Cannot resolve symbol : " + param.type());
		}

		visitStatement(entity.body());
		exitScope();
		return null;
	}

	@Override
	public Void visit(StringConst node) {
		Entity entity = toplevelScope.lookupCurrentLevel(StringType.STRING_CONSTANT + node.value());
		if (entity == null) {
			entity = new StringConstEntity(node.location(), new StringType(), node.value(), node);
			toplevelScope.insert(entity);
		}
		node.setEntity((StringConstEntity)entity);
		return null;
	}

	@Override
	public Void visit(NewExpression node) {
		if (!resolveType(node.type()))
			throw new SemanticError(node.location(), "Cannot resolve symbol : " + node.type());
		if (node.exprs() != null)
			visitExpressions(node.exprs());
		return null;
	}

	@Override
	public Void visit(BlockStatement node) {
		if (firstBlockInFunction) {
			firstBlockInFunction = false;
			node.setScope(currentScope);
			visitStatements(node.statements());
		} else {
			enterScope();
			node.setScope(currentScope);
			visitStatements(node.statements());
			exitScope();
		}
		return null;
	}

	@Override
	public Void visit(VariableNode node) {
		Entity entity = currentScope.lookup(node.name());
		if (entity == null)
			throw new SemanticError(node.location(), "Cannot find variable : " + node.name());
		node.setEntity(entity);

		if (currentClass != null && currentClass.scope().lookupCurrentLevel(node.name()) != null)
			node.setThisPointer(currentThis);

		return null;
	}
}
