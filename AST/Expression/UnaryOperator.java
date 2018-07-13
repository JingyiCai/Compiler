package My.AST.Expression;

import My.FrontEnd.ASTVisitor;
import My.Type.Type;
import My.AST.Location;
import My.Utility.InternalError;

public class UnaryOperator extends Expression {
	public enum UnaryOp {
        PRE_INC, PRE_DEC, SUF_INC, SUF_DEC,
        MINUS, PLUS, LOGIC_NOT, BIT_NOT
    }

	private UnaryOp operator;
    private Expression expr;
    private Type type;

	public UnaryOperator(UnaryOp op, Expression expr) {
		this.operator = op;
		this.expr = expr;
	}

	public UnaryOp operator() {
		return operator;
	}

	public Expression expr() {
		return expr;
	}
	public void setExpr(Expression expr) {
		this.expr = expr;
	}

	public void setType(Type type) {
		if (this.type != null)
			throw new InternalError("Have already set type");
		this.type = type;
	}

	@Override
    public Type type() {
        return expr.type();
    }

	@Override
	public Location location() {
		return expr.location();
	}

	@Override
	public <S, E> E accept(ASTVisitor<S, E> visitor) {
		return visitor.visit(this);
	}
}
