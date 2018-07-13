package My.AST.Expression;

import My.Entity.Entity;
import My.Entity.ParameterEntity;
import My.FrontEnd.ASTVisitor;
import My.Type.Type;
import My.AST.Location;
import My.Utility.InternalError;

public class VariableNode extends LHSNode {
    private Location location;
    private String name;
    private Entity entity;
    private ParameterEntity thisPointer = null;

    public VariableNode(Location loc, String name) {
        this.location = loc;
        this.name = name;
    }

    public VariableNode(Location loc, Entity var) {
        this.entity = var;
        this.location = loc;
        this.name = var.name();
    }

    public String name() {
        return name;
    }

    public Entity entity() {
        if (entity == null) {
            throw new InternalError("Vairable.entity == null");
        }
        return entity;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

	public ParameterEntity getThisPointer() {
        return thisPointer;
    }
    public void setThisPointer(ParameterEntity entity) {
        this.thisPointer = entity;
    }

    public boolean isMember() {
        return thisPointer != null;
    }

    @Override
    public Type type() {
        return entity.type();
    }

    @Override
    public Location location() {
        return location;
    }

    @Override
    public <S, E> E accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
