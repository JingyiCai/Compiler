package My.AST.Expression;

import My.FrontEnd.ASTVisitor;
import My.Type.Type;
import My.AST.Location;

public class SubscriptNode extends LHSNode {
	private Expression expr, index;

	public SubscriptNode(Expression expr, Expression index) {
		this.expr = expr;
		this.index = index;
	}

	public Expression expr() {
		return expr;
	}
	public Expression index() {
		return index;
	}

	public boolean isMultiDimension() {
        return (expr instanceof SubscriptNode);
    }

	public Expression baseExpr() {
		return isMultiDimension() ? ((SubscriptNode)expr).baseExpr() : expr;
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
