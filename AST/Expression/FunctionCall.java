package My.AST.Expression;

import My.FrontEnd.ASTVisitor;
import My.Type.Type;
import My.AST.Location;
import My.Type.FunctionType;

import java.util.List;

public class FunctionCall extends Expression {
	private Expression expr;
	private List<Expression> args;

	public FunctionCall(Expression expr, List<Expression> args) {
		this.expr = expr;
		this.args = args;
	}

	public Expression expr() {
		return expr;
	}

	public List<Expression> args() {
		return args;
	}

	@Override
    public Type type() {
        return functionType().entity().returnType();
    }

	public FunctionType functionType() {
		return (FunctionType)expr.type();
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
