package My.AST.Expression;

import My.FrontEnd.ASTVisitor;
import My.Type.Type;
import My.AST.Location;
import My.Utility.InternalError;

public class BinaryOperator extends Expression {
    public enum BinaryOp {
        ADD, SUB, MUL, DIV, MOD,
        LSHIFT, RSHIFT, LT, GT, LE, GE, EQ, NE,
        BIT_AND, BIT_XOR, BIT_OR,
        LOGIC_AND, LOGIC_OR
    }

    private BinaryOp operator;
    private Expression left, right;
    private Type type;

    public BinaryOperator(Expression left, BinaryOp op, Expression right) {
        super();
        this.operator = op;
        this.left = left;
        this.right = right;
    }

    public BinaryOp operator() {
        return operator;
    }
    public void setOperator(BinaryOp operator) {
        this.operator = operator;
    }

    public Expression left() {
        return left;
    }
    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression right() {
        return right;
    }
    public void setRight(Expression right) {
        this.right = right;
    }

    public void setType(Type type) {
        if (this.type != null)
            throw new InternalError("Have already set type");
        this.type = type;
    }

    @Override
    public Type type() {
        return (type != null) ? type : left.type();
    }

    @Override
    public Location location() {
        return left.location();
    }

    @Override
    public <S, E> E accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
