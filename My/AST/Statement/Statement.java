package My.AST.Statement;

import My.FrontEnd.ASTVisitor;
import My.AST.Node;
import My.AST.Location;

abstract public class Statement extends Node {
	protected Location location;

	public Statement(Location loc) {
		this.location = loc;
	}

	@Override
	public Location location() {
		return location;
	}

	abstract public <S, E> S accept(ASTVisitor<S, E> visitor);
}
