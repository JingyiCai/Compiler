package My.AST.Statement;

import My.FrontEnd.ASTVisitor;
import My.AST.Location;

public class BreakStatement extends Statement {
	public BreakStatement(Location loc) {
		super(loc);
	}

	@Override
    public <S, E> S accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
