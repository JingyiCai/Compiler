package My.AST.Statement;

import My.FrontEnd.ASTVisitor;
import My.Entity.VariableEntity;

public class VariableDefinition extends DefinitionStatement {
	private VariableEntity entity;

	public VariableDefinition(VariableEntity entity) {
		super(entity.location(), entity.name());
		this.entity = entity;
	}

	public VariableEntity entity() {
		return entity;
	}

	@Override
    public <S, E> S accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
