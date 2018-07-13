package My.AST.Expression;

import My.FrontEnd.ASTVisitor;
import My.Entity.Entity;
import My.AST.Location;

public class MemberNode extends LHSNode {
	private Expression expr;
	private String member;
	private Entity entity;

	public MemberNode(Expression expr, String member) {
        this.expr = expr;
        this.member = member;
    }

	public Expression expr() {
        return expr;
    }

    public String member() {
        return member;
    }

    public Entity entity() {
        return entity;
    }
	public void setEntity(Entity entity) {
        this.entity = entity;
    }

	@Override
    public boolean isAssignable() {
        return !entity.type().isFunction();
    }

    @Override
    public Location location() {
        return expr.location();
    }

    @Override
    public <S, E> E accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
