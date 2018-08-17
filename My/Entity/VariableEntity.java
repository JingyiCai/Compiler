package My.Entity;

import My.AST.Expression.Expression;
import My.AST.Location;
import My.Type.*;
import My.Utility.SemanticError;

public class VariableEntity extends Entity {
    private Expression init;

    public VariableEntity(Location loc, Type type, String name, Expression init) {
        super(loc, type, name);
        if ((type instanceof VoidType) || (type instanceof  ArrayType) && (((ArrayType)type).deepType() instanceof VoidType))
            throw new SemanticError(loc, "variable type can not be void");
        this.init = init;
    }

    public Expression init() {
        return init;
    }

    @Override
    public String toString() {
        return "variable entity : " + name;
    }
}
