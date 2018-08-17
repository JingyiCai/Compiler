package My.AST.Statement;

import My.FrontEnd.ASTVisitor;
import My.AST.Location;
import My.AST.Expression.Expression;

public class ReturnStatement extends Statement {
	protected Expression expr;

	public ReturnStatement(Location loc, Expression expr) {
		super(loc);
		this.expr = expr;
	}

	public Expression expr() {
		return expr;
	}
	public void setExpr(Expression expr) {
		this.expr = expr;
	}

	@Override
    public <S, E> S accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
