package My.AST.Expression;

import My.FrontEnd.ASTVisitor;
import My.Type.Type;
import My.Type.ArrayType;
import My.AST.Location;

import java.util.List;

public class NewExpression extends Expression {
	private Location location;
    private Type type;
    private List<Expression> exprs;
    private int dimension;

    public NewExpression(Location loc, Type type, List<Expression> exprs, int dimension) {
        this.location = loc;
        this.type = type;
        this.exprs = exprs;
        this.dimension = dimension;
    }

    public List<Expression> exprs() {
        return exprs;
    }
    public void setExprs(List<Expression> exprs) {
        this.exprs = exprs;
    }

    public int dimension() {
        return dimension;
    }

	@Override
	public Type type() {
        if (dimension > 0)
	        return new ArrayType(type, dimension);
        else
            return type;
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
