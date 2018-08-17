package My.Entity;

import My.AST.Expression.Expression;
import My.AST.Location;
import My.Type.Type;
import My.Type.StringType;

public class StringConstEntity extends Entity {
	private Expression expr;

	public StringConstEntity(Location loc, Type type, String name, Expression expr) {
		super(loc, type, StringType.STRING_CONSTANT + name);
		this.expr = expr;
	}

	public String name() {
		return name;
	}

	@Override
    public String toString() {
        return "constant entity : " + name;
    }
}
