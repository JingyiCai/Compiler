package My.AST.Expression;

import My.FrontEnd.ASTVisitor;
import My.Type.Type;
import My.AST.Node;

abstract public class Expression extends Node {
    private boolean isAssignable = false;

    public Expression() {
        super();
    }

    abstract public Type type();

    public boolean isConstant() {
        return false;
    }
    public boolean isParameter() {
        return false;
    }
    public boolean isLvalue() {
        return false;
    }

	public boolean isAssignable() {
		return isAssignable;
	}
    public void setAssignable(boolean isAssignable) {
        this.isAssignable = isAssignable;
    }

    abstract public <S,E> E accept(ASTVisitor<S, E> visitor);
}
