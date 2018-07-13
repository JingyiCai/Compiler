package My.AST.Expression;

import My.Type.Type;
import My.AST.Location;

abstract public class Constant extends Expression {
	protected Location location;
	protected Type type;

	public Constant(Location loc, Type type) {
		super();
		this.location = loc;
		this.type = type;
	}

	@Override
	public boolean isConstant() {
		return true;
	}

	@Override
	public Type type() {
		return type;
	}

	@Override
	public Location location() {
		return location;
	}
}
