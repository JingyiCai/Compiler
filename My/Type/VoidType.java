package My.Type;

public class VoidType extends Type {
    public VoidType() {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isVoid() {
        return true;
    }

    @Override
    public boolean isCompatible(Type other) {
        return other.isVoid();
    }

    @Override
    public String toString() {
        return "void";
    }
}
