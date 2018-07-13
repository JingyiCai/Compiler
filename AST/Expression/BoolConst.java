package My.AST.Expression;

import My.FrontEnd.ASTVisitor;
import My.Type.BoolType;
import My.AST.Location;

public class BoolConst extends Constant {
	private boolean value;

	public BoolConst(Location loc, boolean value) {
		super(loc, new BoolType());
		this.value = value;
	}

	public boolean value() {
		return value;
	}

	@Override
    public <S, E> E accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
