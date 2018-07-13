package My.AST.Expression;

import My.Type.Type;

abstract public class LHSNode extends Expression {
    protected Type type;

    @Override
    public Type type() {
        return type;
    }

    public void setType(Type t) {
        this.type = t;
    }

    @Override
    public boolean isLvalue() {
		return true;
	}

    @Override
    public boolean isAssignable() {
		return true;
	}
}
