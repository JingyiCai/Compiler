package My.AST.Expression;

import My.FrontEnd.ASTVisitor;
import My.Type.IntegerType;
import My.AST.Location;

public class IntegerConst extends Constant {
	private long value;

	public IntegerConst(Location loc, long value) {
		super(loc, new IntegerType());
		this.value = value;
	}

	public long value() {
		return value;
	}

	@Override
    public <S, E> E accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
