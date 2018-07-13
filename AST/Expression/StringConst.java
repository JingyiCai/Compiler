package My.AST.Expression;

import My.Entity.StringConstEntity;
import My.FrontEnd.ASTVisitor;
import My.Type.StringType;
import My.AST.Location;

public class StringConst extends Constant {
	private String value;
	private StringConstEntity entity;

	public StringConst(Location loc, String value) {
		super(loc, new StringType());
		this.value = value;
	}

	public String value() {
		return value;
	}

	public StringConstEntity entity() {
		return entity;
	}
	public void setEntity(StringConstEntity entity) {
		this.entity = entity;
	}

	@Override
    public <S, E> E accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
