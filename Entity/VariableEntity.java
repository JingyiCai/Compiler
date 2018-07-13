package My.Entity;

import My.AST.Expression.Expression;
import My.AST.Location;
import My.Type.Type;

public class VariableEntity extends Entity {
    private Expression init;

    public VariableEntity(Location loc, Type type, String name, Expression init) {
        super(loc, type, name);
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
