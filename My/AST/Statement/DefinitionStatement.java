package My.AST.Statement;

import My.FrontEnd.ASTVisitor;
import My.AST.Location;

abstract public class DefinitionStatement extends Statement {
	protected String name;

	public DefinitionStatement(Location loc, String name) {
		super(loc);
		this.name = name;
	}

	public String name() {
		return name;
	}

	abstract public <S, E> S accept(ASTVisitor<S, E> visitor);
}
