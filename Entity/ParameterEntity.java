package My.Entity;

import My.AST.Location;
import My.Type.Type;

public class ParameterEntity extends Entity {
	public ParameterEntity(Location loc, Type type, String name) {
		super(loc, type, name);
	}
}
