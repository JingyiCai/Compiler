package My.AST.Statement;

import My.FrontEnd.ASTVisitor;
import My.AST.Location;
import My.AST.Expression.Expression;

public class WhileStatement extends Statement {
	private Expression cond;
	private Statement body;

	public WhileStatement(Location loc, Expression cond,Statement body) {
		super(loc);
		this.cond = cond;
		this.body = BlockStatement.wrapBlock(body);
	}

    public Expression cond() {
        return cond;
    }

    public Statement body() {
        return body;
    }

    @Override
    public <S, E> S accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
