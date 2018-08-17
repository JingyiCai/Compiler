package My.Entity;

import My.AST.Expression.Expression;
import My.AST.Location;
import My.Type.Type;
import My.Utility.InternalError;

abstract public class Entity {
	protected Location location;
	protected String name;
	protected Type type;

	public Entity(Location loc, Type type, String name) {
        this.location = loc;
        this.type = type;
        this.name = name;
    }

	public Location location() {
		return location;
	}

	public String name() {
        return name;
    }

    public Type type() {
        return type;
    }

    public Expression value() {
        throw new InternalError("Called Entity value");
    }

    public int size() {
        return type.size();
    }
}
