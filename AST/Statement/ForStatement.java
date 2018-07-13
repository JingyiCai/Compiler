package My.AST.Statement;

import My.FrontEnd.ASTVisitor;
import My.AST.Location;
import My.AST.Expression.Expression;

public class ForStatement extends Statement {
	private Expression init, cond, step;
	private Statement body;

	public ForStatement(Location loc, Expression init, Expression cond, Expression step, Statement body) {
		super(loc);
		this.init = init;
		this.cond = cond;
		this.step = step;
		this.body = BlockStatement.wrapBlock(body);
	}

	public Expression init() {
        return init;
    }

    public Expression cond() {
        return cond;
    }

    public Expression step() {
        return step;
    }

    public Statement body() {
        return body;
    }

    @Override
    public <S, E> S accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
