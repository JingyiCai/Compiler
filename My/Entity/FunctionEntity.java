package My.Entity;

import My.AST.*;
import My.AST.Expression.*;
import My.AST.Statement.*;
import My.Type.FunctionType;
import My.Type.Type;

import java.util.*;

public class FunctionEntity extends Entity {
	private Type returnType;
	private List<ParameterEntity> params;
	private BlockStatement body;
	private Scope scope;

	private boolean isConstructor = false;
	private boolean isLibFunction = false;

	public FunctionEntity(Location loc, Type returnType, String name, List<ParameterEntity> params, BlockStatement body) {
		super(loc, new FunctionType(name), name);
		this.returnType = returnType;
		this.params = params;
		this.body = body;
		((FunctionType)this.type).setEntity(this);
	}

	public ParameterEntity addThisPointer(Location loc, ClassEntity entity) {
		ParameterEntity thisPointer = new ParameterEntity(loc, entity.type(), "this");
		params.add(0, thisPointer);
		return thisPointer;
	}

	public Type returnType() {
        return returnType;
    }

	public List<ParameterEntity> params() {
        return params;
    }

	public BlockStatement body() {
        return body;
    }

	public Scope scope() {
        return scope;
    }
    public void setScope(Scope scope) {
        this.scope = scope;
    }

	public boolean isConstructor() {
        return isConstructor;
    }
    public void setConstructor(boolean constructor) {
        isConstructor = constructor;
    }

	public boolean isLibFunction() {
		return isLibFunction;
	}
	public void setLibFunction(boolean libFunction) {
		isLibFunction = libFunction;
	}

	@Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "function entity : " + name;
    }
}
