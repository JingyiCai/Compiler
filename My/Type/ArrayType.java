package My.Type;

import My.AST.Location;
import My.Entity.FunctionEntity;
import My.Entity.ParameterEntity;
import My.Entity.Scope;

import java.util.LinkedList;
import java.util.List;

public class ArrayType extends Type {
	private Type baseType;
	static private Scope scope;
	static public Scope scope() {
		return scope;
	}

	static public void initBuiltinFunction() {
		scope = new Scope(false);
		List<ParameterEntity> param = new LinkedList<>();
		param.add(new ParameterEntity(new Location(0, 0), new ArrayType(nullType), "this"));
		scope.insert(new FunctionEntity(new Location(0, 0), integerType, "size", param, null));

	}

	public ArrayType(Type baseType) {
		this.baseType = baseType;
	}

	public ArrayType(Type baseType, int dimension) {
		if (dimension == 1)
			this.baseType = baseType;
		else
			this.baseType = new ArrayType(baseType, dimension - 1);
	}

	public Type baseType() {
		return baseType;
	}

	public Type deepType() {
		return baseType instanceof ArrayType ? ((ArrayType)baseType).deepType() : baseType;
	}

	@Override
    public boolean isArray() {
        return true;
    }

	@Override
    public boolean isHalfComparable() {
        return true;
    }

	@Override
    public boolean isCompatible(Type other) {
        if (other.isNull())
			return true;
        if (!other.isArray())
			return false;
        return baseType.isCompatible(((ArrayType)other).baseType());
    }

	@Override
    public int size() {
        return 4;
    }

    @Override
    public String toString() {
        return baseType.toString() + "[]";
    }
}
