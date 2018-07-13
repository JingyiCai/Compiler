package My.FrontEnd;

import My.AST.Expression.*;
import My.AST.Statement.*;

public interface ASTVisitor<S, E> {
	//Statements
	S visit(BlockStatement node);
	S visit(BreakStatement node);
	S visit(ContinueStatement node);
	S visit(ReturnStatement node);
	S visit(IfStatement node);
	S visit(ForStatement node);
	S visit(WhileStatement node);
	S visit(ExpressionStatement node);
	//Definitions
	S visit(VariableDefinition node);
	S visit(FunctionDefinition node);
	S visit(ClassDefinition node);

	//Expressions
	E visit(Assign node);
	E visit(FunctionCall node);
	E visit(NewExpression node);
	//E visit(LSHNode node);
	E visit(VariableNode node);
	E visit(SubscriptNode node);
	E visit(MemberNode node);
	//E visit(Constant node);
	E visit(BoolConst node);
	E visit(IntegerConst node);
	E visit(StringConst node);
	E visit(UnaryOperator node);
	E visit(PrefixOperator node);
	E visit(SuffixOperator node);
	E visit(BinaryOperator node);
}
