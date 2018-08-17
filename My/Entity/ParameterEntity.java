package My.Entity;

import My.AST.Location;
import My.Type.*;
import My.Utility.SemanticError;

public class ParameterEntity extends Entity {
	public ParameterEntity(Location loc, Type type, String name) {
		super(loc, type, name);
		if ((type instanceof VoidType) || (type instanceof ArrayType) && (((ArrayType)type).deepType() instanceof VoidType))
			throw new SemanticError(loc, "parameter type can not be void");
	}
}
