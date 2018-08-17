package My.AST.Statement;

import My.FrontEnd.ASTVisitor;
import My.AST.Location;
import My.AST.Expression.Expression;

public class IfStatement extends Statement {
	private Expression cond;
	private Statement thenBody, elseBody;

	public IfStatement(Location loc, Expression cond, Statement thenBody, Statement elseBody) {
		super(loc);
		this.cond = cond;
		this.thenBody = BlockStatement.wrapBlock(thenBody);
		this.elseBody = BlockStatement.wrapBlock(elseBody);
	}

	public Expression cond() {
		return cond;
	}

	public Statement thenBody() {
		return thenBody;
	}

	public Statement elseBody() {
		return elseBody;
	}

	@Override
    public <S, E> S accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
