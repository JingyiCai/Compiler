package My.FrontEnd;

import My.AST.Expression.*;
import My.AST.Statement.*;

import java.util.List;

abstract public class Visitor implements ASTVisitor<Void, Void> {
	public void visitStatement(Statement stmt) {
		stmt.accept(this);
	}
	public void visitStatements(List<? extends Statement> stmts) {
		for (Statement stmt : stmts)
			visitStatement(stmt);
	}

	public void visitDefinition(DefinitionStatement def) {
		def.accept(this);
	}
	public void visitDefinitions(List<? extends DefinitionStatement> defs) {
		for (DefinitionStatement def : defs)
			visitDefinition(def);
	}

	public void visitExpression(Expression expr) {
		if (expr != null) expr.accept(this);
	}
	public void visitExpressions(List<? extends Expression> exprs) {
		for (Expression expr : exprs)
			visitExpression(expr);
	}

	/*
	** Statement
	*/
	@Override
	public Void visit(BlockStatement node) {
		visitStatements(node.statements());
		return null;
	}

	@Override
	public Void visit(BreakStatement node) {
		return null;
	}

	@Override
	public Void visit(ContinueStatement node) {
		return null;
	}

	@Override
	public Void visit(ReturnStatement node) {
		if (node.expr() != null)
			visitExpression(node.expr());
		return null;
	}

	@Override
	public Void visit(IfStatement node) {
		visitExpression(node.cond());
		if (node.thenBody() != null)
			visitStatement(node.thenBody());
		if (node.elseBody() != null)
			visitStatement(node.elseBody());
		return null;
	}

	@Override
	public Void visit(ForStatement node) {
		if (node.init() != null)
			visitExpression(node.init());
		if (node.cond() != null)
			visitExpression(node.cond());
		if (node.step() != null)
			visitExpression(node.step());
		if (node.body() != null)
			visitStatement(node.body());
		return null;
	}

	@Override
	public Void visit(WhileStatement node) {
		visitExpression(node.cond());
		if (node.body() != null)
			visitStatement(node.body());
		return null;
	}

	@Override
	public Void visit(ExpressionStatement node) {
		visitExpression(node.expr());
		return null;
	}

	/*
	** Definition
	*/
	@Override
	public Void visit(VariableDefinition node) {
		if (node.entity().init() != null)
			visitExpression(node.entity().init());
		return null;
	}

	@Override
	public Void visit(FunctionDefinition node) {
		visitStatement(node.entity().body());
		return null;
	}

	@Override
	public Void visit(ClassDefinition node) {
		visitStatements(node.entity().memberVars());
		visitStatements(node.entity().memberFuncs());
		return null;
	}

	/*
	** Expression
	*/
	@Override
	public Void visit(Assign node) {
		visitExpression(node.lhs());
		visitExpression(node.rhs());
		return null;
	}

	@Override
	public Void visit(FunctionCall node) {
		visitExpression(node.expr());
		if (node.args() != null)
			visitExpressions(node.args());
		return null;
	}

	@Override
	public Void visit(NewExpression node) {
		if (node.exprs() != null)
			visitExpressions(node.exprs());
		return null;
	}

	@Override
	public Void visit(VariableNode node) {
		return null;
	}

	@Override
	public Void visit(SubscriptNode node) {
		visitExpression(node.expr());
		visitExpression(node.index());
		return null;
	}

	@Override
	public Void visit(MemberNode node) {
		visitExpression(node.expr());
		return null;
	}

	@Override
	public Void visit(BoolConst node) {
		return null;
	}

	@Override
	public Void visit(IntegerConst node) {
		return null;
	}

	@Override
	public Void visit(StringConst node) {
		return null;
	}

	@Override
	public Void visit(UnaryOperator node) {
		visitExpression(node.expr());
		return null;
	}

	@Override
	public Void visit(PrefixOperator node) {
		visitExpression(node.expr());
		return null;
	}

	@Override
	public Void visit(SuffixOperator node) {
		visitExpression(node.expr());
		return null;
	}

	@Override
	public Void visit(BinaryOperator node) {
		visitExpression(node.left());
		visitExpression(node.right());
		return null;
	}
}
