package My.AST.Expression;

import My.FrontEnd.ASTVisitor;

public class PrefixOperator extends UnaryOperator {
	public PrefixOperator(UnaryOp op, Expression expr) {
		super(op, expr);
	}

	@Override
	public <S, E> E accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
