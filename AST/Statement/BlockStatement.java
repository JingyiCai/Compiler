package My.AST.Statement;

import My.FrontEnd.ASTVisitor;
import My.Entity.Scope;
import My.AST.Location;

import java.util.LinkedList;
import java.util.List;

public class BlockStatement extends Statement {
	private List<Statement> statements;
	private Scope scope;

	public BlockStatement(Location loc, List<Statement> statements) {
		super(loc);
		this.statements = statements;
	}

	public static BlockStatement wrapBlock(Statement statement) {
		if (statement == null)
			return null; //new BlockStatement(new Location(0, 0), new LinkedList<>());

		if (statement instanceof BlockStatement) {
			return (BlockStatement)statement;
		} else {
			return new BlockStatement(statement.location(),
							new LinkedList<Statement>() {{ add(statement); }});
		}
	}

	public List<Statement> statements() {
		return statements;
	}

	public Scope scope() {
		return scope;
	}
	public void setScope(Scope scope) {
		this.scope = scope;
	}

	@Override
    public <S, E> S accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
