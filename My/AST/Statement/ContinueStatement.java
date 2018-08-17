package My.AST.Statement;

import My.FrontEnd.ASTVisitor;
import My.AST.Location;

public class ContinueStatement extends Statement {
	public ContinueStatement(Location loc) {
		super(loc);
	}

	@Override
    public <S, E> S accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
