package My.Type;

abstract public class Type {
	static public NullType nullType = new NullType();
	static public VoidType voidType = new VoidType();
	static public BoolType boolType = new BoolType();
	static public IntegerType integerType = new IntegerType();
	static public StringType stringType = new StringType();

	public boolean isNull() {
        return false;
    }
	public boolean isVoid() {
        return false;
    }
    public boolean isBool() {
        return false;
    }
    public boolean isInteger() {
        return false;
    }
    public boolean isString() {
        return false;
    }
	public boolean isArray() {
        return false;
    }
    public boolean isFunction() {
        return false;
    }
    public boolean isClass() {
        return false;
    }

	public boolean isFullComparable() {
        return false;
    }
    public boolean isHalfComparable() {
        return false;
    }

	abstract public boolean isCompatible(Type other);

    abstract public int size();

    public long allocSize() {
        return size();
    }

	//public String toString();
}
