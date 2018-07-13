package My.AST.Expression;

import My.FrontEnd.ASTVisitor;

public class SuffixOperator extends UnaryOperator {
	public SuffixOperator(UnaryOp op, Expression expr) {
		super(op, expr);
	}

	@Override
	public <S, E> E accept(ASTVisitor<S, E> visitor) {
        return visitor.visit(this);
    }
}
