package My.AST.Expression;

import My.FrontEnd.ASTVisitor;
import My.Type.Type;
import My.AST.Location;

public class Assign extends Expression {
    private Expression lhs, rhs;

    public Assign(Expression lhs, Expression rhs) {
        super();
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Expression lhs() {
        return lhs;
    }
    public void setLhs(Expression lhs) {
        this.lhs = lhs;
    }

    public Expression rhs() {
        return rhs;
    }
    public void setRhs(Expression rhs) {
        this.rhs = rhs;
    }

    @Override
    public Type type() {
        return lhs.type();
    }

    @Override
    public Location location() {
        return lhs.location();
    }

    @Override
    public <S, E> E accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
