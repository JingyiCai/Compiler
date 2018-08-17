// Generated from Mxll.g4 by ANTLR 4.7
package My.Parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxllParser}.
 */
public interface MxllListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxllParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxllParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxllParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxllParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxllParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void enterClassDefinition(MxllParser.ClassDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxllParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void exitClassDefinition(MxllParser.ClassDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxllParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(MxllParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxllParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(MxllParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxllParser#variableDefinition}.
	 * @param ctx the parse tree
	 */
	void enterVariableDefinition(MxllParser.VariableDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxllParser#variableDefinition}.
	 * @param ctx the parse tree
	 */
	void exitVariableDefinition(MxllParser.VariableDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxllParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(MxllParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxllParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(MxllParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxllParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MxllParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxllParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MxllParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxllParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(MxllParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxllParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(MxllParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxllParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(MxllParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxllParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(MxllParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxllParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(MxllParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxllParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(MxllParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxllParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(MxllParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxllParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(MxllParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxllParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MxllParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxllParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MxllParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link MxllParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(MxllParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link MxllParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(MxllParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link MxllParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(MxllParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link MxllParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(MxllParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link MxllParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MxllParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link MxllParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MxllParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpression(MxllParser.BinaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpression(MxllParser.BinaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prefixExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixExpression(MxllParser.PrefixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prefixExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixExpression(MxllParser.PrefixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(MxllParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(MxllParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptExpression(MxllParser.SubscriptExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptExpression(MxllParser.SubscriptExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterThisExpression(MxllParser.ThisExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitThisExpression(MxllParser.ThisExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suffixExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuffixExpression(MxllParser.SuffixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suffixExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuffixExpression(MxllParser.SuffixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpression(MxllParser.NewExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpression(MxllParser.NewExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignmentExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(MxllParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignmentExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(MxllParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(MxllParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(MxllParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpression(MxllParser.MemberExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpression(MxllParser.MemberExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpression(MxllParser.VariableExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpression(MxllParser.VariableExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubExpression(MxllParser.SubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link MxllParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubExpression(MxllParser.SubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link MxllParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(MxllParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link MxllParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(MxllParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intType}
	 * labeled alternative in {@link MxllParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(MxllParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link MxllParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(MxllParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link MxllParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStringType(MxllParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link MxllParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStringType(MxllParser.StringTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code voidType}
	 * labeled alternative in {@link MxllParser#type}.
	 * @param ctx the parse tree
	 */
	void enterVoidType(MxllParser.VoidTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code voidType}
	 * labeled alternative in {@link MxllParser#type}.
	 * @param ctx the parse tree
	 */
	void exitVoidType(MxllParser.VoidTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link MxllParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(MxllParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link MxllParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(MxllParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classType}
	 * labeled alternative in {@link MxllParser#type}.
	 * @param ctx the parse tree
	 */
	void enterClassType(MxllParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classType}
	 * labeled alternative in {@link MxllParser#type}.
	 * @param ctx the parse tree
	 */
	void exitClassType(MxllParser.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolConstant}
	 * labeled alternative in {@link MxllParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterBoolConstant(MxllParser.BoolConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolConstant}
	 * labeled alternative in {@link MxllParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitBoolConstant(MxllParser.BoolConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intConstant}
	 * labeled alternative in {@link MxllParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterIntConstant(MxllParser.IntConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intConstant}
	 * labeled alternative in {@link MxllParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitIntConstant(MxllParser.IntConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link MxllParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterStringConstant(MxllParser.StringConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link MxllParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitStringConstant(MxllParser.StringConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullConstant}
	 * labeled alternative in {@link MxllParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterNullConstant(MxllParser.NullConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullConstant}
	 * labeled alternative in {@link MxllParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitNullConstant(MxllParser.NullConstantContext ctx);
}