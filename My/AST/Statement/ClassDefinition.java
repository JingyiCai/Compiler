package My.AST.Statement;

import My.FrontEnd.ASTVisitor;
import My.Entity.ClassEntity;

public class ClassDefinition extends DefinitionStatement {
	private ClassEntity entity;

	public ClassDefinition(ClassEntity entity) {
		super(entity.location(), entity.name());
		this.entity = entity;
	}

	public ClassEntity entity() {
		return entity;
	}

	@Override
    public <S, E> S accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
