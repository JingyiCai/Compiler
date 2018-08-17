package My.Type;

import My.AST.Location;
import My.Entity.FunctionEntity;
import My.Entity.ParameterEntity;
import My.Entity.Scope;

import java.util.LinkedList;
import java.util.List;

public class StringType extends Type {
	static final int DEFAULT_SIZE = 8;
	static final public String STRING_CONSTANT = "__strConstant_";

	static private Scope scope;
	static public Scope scope() {
		return scope;
	}

	static public void initBuiltinFunction() {
		scope = new Scope(false);
		Location loc = new Location(0, 0);
		List<ParameterEntity> param = new LinkedList<>();
		param.add(new ParameterEntity(loc, stringType, "this"));
		scope.insert(new FunctionEntity(loc, integerType, "length", param, null));
		scope.insert(new FunctionEntity(loc, integerType, "parseInt", param, null));
		param = new LinkedList<>();
		param.add(new ParameterEntity(loc, stringType, "this"));
		param.add(new ParameterEntity(loc, integerType, "pos"));
		scope.insert(new FunctionEntity(loc, integerType, "ord", param, null));
		param = new LinkedList<>();
		param.add(new ParameterEntity(loc, stringType, "this"));
		param.add(new ParameterEntity(loc, integerType, "left"));
		param.add(new ParameterEntity(loc, integerType, "right"));
		scope.insert(new FunctionEntity(loc, stringType, "substring", param, null));
	}

	@Override
	public boolean isString() {
		return true;
	}

	@Override
	public boolean isFullComparable() {
		return true;
	}

	@Override
	public boolean isHalfComparable() {
		return true;
	}

	@Override
	public boolean isCompatible(Type other) {
		return other.isString();
	}

	@Override
    public int size() {
        return DEFAULT_SIZE;
    }

    @Override
    public String toString() {
        return "string";
    }
}
