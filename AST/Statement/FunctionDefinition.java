package My.AST.Statement;

import My.FrontEnd.ASTVisitor;
import My.Entity.FunctionEntity;

public class FunctionDefinition extends DefinitionStatement {
	private FunctionEntity entity;

	public FunctionDefinition(FunctionEntity entity) {
		super(entity.location(), entity.name());
		this.entity = entity;
	}

	public FunctionEntity entity() {
		return entity;
	}

	@Override
    public <S, E> S accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
