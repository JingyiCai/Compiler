package My.FrontEnd;

import My.AST.Location;
import My.AST.Expression.*;
import My.AST.Statement.*;
import My.Entity.ClassEntity;
import My.Entity.FunctionEntity;
import My.Entity.ParameterEntity;
import My.Entity.VariableEntity;
import My.Type.*;
import My.Utility.InternalError;
import My.Utility.SemanticError;
import My.Parser.MxllBaseListener;
import My.Parser.MxllParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.LinkedList;
import java.util.List;

public class ASTBuilder extends MxllBaseListener {
	private ParseTreeProperty<Object> rst = new ParseTreeProperty<>();
	private AST ast;

	public AST getAST() {
		return ast;
	}

	@Override public void exitProgram(MxllParser.ProgramContext ctx) {
		List<DefinitionStatement> defNodes = new LinkedList<>();
		List<ClassEntity> classEntities = new LinkedList<>();
		List<FunctionEntity> functionEntities = new LinkedList<>();
		List<VariableEntity> variableEntities = new LinkedList<>();

		for (ParserRuleContext parserRuleContext : ctx.getRuleContexts(ParserRuleContext.class)) {
			if (parserRuleContext instanceof  MxllParser.VariableDefinitionContext) {
				List<VariableDefinition> nodeList = (List<VariableDefinition>) rst.get(parserRuleContext);
				for (VariableDefinition node : nodeList)
					variableEntities.add(node.entity());
			}
			else {
				DefinitionStatement node = (DefinitionStatement) rst.get(parserRuleContext);
				defNodes.add(node);
				if (node instanceof ClassDefinition)
					classEntities.add(((ClassDefinition) node).entity());
				else if (node instanceof FunctionDefinition)
					functionEntities.add(((FunctionDefinition) node).entity());
				else
					throw new InternalError("Invalid definition node " + node.name());
			}
		}

		ast = new AST(defNodes, classEntities, functionEntities, variableEntities);
	}

	@Override public void exitClassDefinition(MxllParser.ClassDefinitionContext ctx) {
		List<VariableDefinition> vars = new LinkedList<>();
		List<FunctionDefinition> funcs = new LinkedList<>();
		String name = ctx.Identifier().getText();

		for (MxllParser.VariableDefinitionContext item : ctx.variableDefinition()) {
			List<VariableDefinition> varlist = (List<VariableDefinition>) rst.get(item);
			for (int i = 0; i < varlist.size(); ++i)
				vars.add(varlist.get(i));
		}

		FunctionEntity constructor = null;
		for (MxllParser.FunctionDefinitionContext item : ctx.functionDefinition()) {
			FunctionDefinition node = (FunctionDefinition)rst.get(item);
			funcs.add(node);
			FunctionEntity entity = node.entity();
			if (entity.isConstructor()) {
				constructor = entity;
				if (!entity.name().equals(ClassType.CONSTRUCTOR_NAME + name))
					throw new SemanticError(new Location(ctx.Identifier()), "wrong constructorName " + entity.name() + " of class " + name);
			}
		}

		ClassEntity entity = new ClassEntity(new Location(ctx.Identifier()), name, vars, funcs);
		entity.setConstructor(constructor);

		rst.put(ctx, new ClassDefinition(entity));
	}

	@Override public void exitFunctionDefinition(MxllParser.FunctionDefinitionContext ctx) {
		List<ParameterEntity> params = new LinkedList<>();

		for (MxllParser.ParameterContext item : ctx.parameter())
			params.add((ParameterEntity)rst.get(item));

		FunctionEntity entity = null;
		if (ctx.type() == null) { //constructor
			entity = new FunctionEntity(new Location(ctx.Identifier()),
										new ClassType(ctx.Identifier().getText()),
										ClassType.CONSTRUCTOR_NAME + ctx.Identifier().getText(),
										params, (BlockStatement)rst.get(ctx.blockStatement()) );
			entity.setConstructor(true);
		} else {
			entity = new FunctionEntity(new Location(ctx.Identifier()),
										(Type)rst.get(ctx.type()),
										ctx.Identifier().getText(),
										params, (BlockStatement)rst.get(ctx.blockStatement()) );
		}

		rst.put(ctx, new FunctionDefinition(entity));
	}

	@Override public void exitVariableDefinition(MxllParser.VariableDefinitionContext ctx) {
		List<VariableDefinition> vars = new LinkedList<>();
		for (int i = 0; i < ctx.Identifier().size(); ++i) {
			VariableEntity entity = new VariableEntity(new Location(ctx.Identifier(i)), (Type) rst.get(ctx.type()),
					ctx.Identifier(i).getText(), getExpr(ctx.expression(i)));
			vars.add(new VariableDefinition(entity));
		}
		rst.put(ctx, vars);
	}

	@Override public void exitParameter(MxllParser.ParameterContext ctx) {
		rst.put(ctx, new ParameterEntity(new Location(ctx.Identifier()), (Type)rst.get(ctx.type()), ctx.Identifier().getText()));
	}

	@Override public void exitStatement(MxllParser.StatementContext ctx) {
		if (ctx.blockStatement() != null) rst.put(ctx, rst.get(ctx.blockStatement()));
		else if (ctx.variableDefinition() != null) rst.put(ctx, rst.get(ctx.variableDefinition()));
		else if (ctx.expressionStatement() != null) rst.put(ctx, rst.get(ctx.expressionStatement()));
		else if (ctx.ifStatement() != null) rst.put(ctx, rst.get(ctx.ifStatement()));
		else if (ctx.forStatement() != null) rst.put(ctx, rst.get(ctx.forStatement()));
		else if (ctx.whileStatement() != null) rst.put(ctx, rst.get(ctx.whileStatement()));
		else if (ctx.jumpStatement() != null) rst.put(ctx, rst.get(ctx.jumpStatement()));
	}

	@Override public void exitBlockStatement(MxllParser.BlockStatementContext ctx) {
		List<Statement> stmts = new LinkedList<>();
		for (MxllParser.StatementContext item : ctx.statement()) {
			if (item.variableDefinition() != null) {
				List<Statement> stmt = getStmts(item);
				for (int i = 0; i < stmt.size(); ++i)
					stmts.add(stmt.get(i));
			} else {
				Statement stmt = getStmt(item);
				if (stmt != null)
					stmts.add(stmt);
			}
		}
		rst.put(ctx, new BlockStatement(new Location(ctx), stmts));
	}

	@Override public void exitExpressionStatement(MxllParser.ExpressionStatementContext ctx) {
		rst.put(ctx, new ExpressionStatement(new Location(ctx), getExpr(ctx.expression())));
	}

	@Override public void exitIfStatement(MxllParser.IfStatementContext ctx) {
		rst.put(ctx, new IfStatement(new Location(ctx), getExpr(ctx.expression()),
									 getStmt(ctx.statement(0)), getStmt(ctx.statement(1)) ));
	}
	@Override public void exitForStatement(MxllParser.ForStatementContext ctx) {
		rst.put(ctx, new ForStatement(new Location(ctx), getExpr(ctx.expression(0)), getExpr(ctx.expression(1)),
									  getExpr(ctx.expression(2)), getStmt(ctx.statement()) ));
	}
	@Override public void exitWhileStatement(MxllParser.WhileStatementContext ctx) {
		rst.put(ctx, new WhileStatement(new Location(ctx), getExpr(ctx.expression()), getStmt(ctx.statement()) ));
	}

	@Override public void exitContinueStatement(MxllParser.ContinueStatementContext ctx) {
		rst.put(ctx, new ContinueStatement(new Location(ctx)));
	}
	@Override public void exitBreakStatement(MxllParser.BreakStatementContext ctx) {
		rst.put(ctx, new BreakStatement(new Location(ctx)));
	}
	@Override public void exitReturnStatement(MxllParser.ReturnStatementContext ctx) {
		rst.put(ctx, new ReturnStatement(new Location(ctx), getExpr(ctx.expression())));
	}

	@Override public void exitThisExpression(MxllParser.ThisExpressionContext ctx) {
		rst.put(ctx, new VariableNode(new Location(ctx), "this"));
	}

	@Override public void exitConstantExpression(MxllParser.ConstantExpressionContext ctx) {
		rst.put(ctx, rst.get(ctx.constant()));
	}

	@Override public void exitVariableExpression(MxllParser.VariableExpressionContext ctx) {
		rst.put(ctx, new VariableNode(new Location(ctx.Identifier()), ctx.Identifier().getText()));
	}

	@Override public void exitSubExpression(MxllParser.SubExpressionContext ctx) {
		rst.put(ctx, rst.get(ctx.expression()));
	}

	@Override public void exitMemberExpression(MxllParser.MemberExpressionContext ctx) {
		rst.put(ctx, new MemberNode(getExpr(ctx.expression()), ctx.Identifier().getText()));
	}

	@Override public void exitSubscriptExpression(MxllParser.SubscriptExpressionContext ctx) {
		rst.put(ctx, new SubscriptNode(getExpr(ctx.expression(0)), getExpr(ctx.expression(1))));
	}

	@Override public void exitFunctionCallExpression(MxllParser.FunctionCallExpressionContext ctx) {
		List<Expression> args = new LinkedList<>();
		for (int i = 1; i < ctx.expression().size(); ++i)
			args.add(getExpr(ctx.expression(i)));
		rst.put(ctx, new FunctionCall(getExpr(ctx.expression(0)), args));
	}

	@Override public void exitNewExpression(MxllParser.NewExpressionContext ctx) {
		Type baseType = (Type)rst.get(ctx.type());
		List<Expression> exprs = new LinkedList<>();
		for (MxllParser.ExpressionContext item : ctx.expression())
			exprs.add(getExpr(item));
		int exprNum = ctx.expression().size();
		int dimension = ctx.getChildCount() - 2 - exprNum * 2;

		if (baseType instanceof ArrayType) {
			throw new SemanticError(new Location(ctx), "Invalid new expression");
		} else {
			if (exprNum == 0 && dimension > 0)
				throw new SemanticError(new Location(ctx), "Invalid new expression");
			rst.put(ctx, new NewExpression(new Location(ctx), baseType, exprs, dimension));
		}
	}

	@Override public void exitSuffixExpression(MxllParser.SuffixExpressionContext ctx) {
		UnaryOperator.UnaryOp op;
		switch (ctx.op.getText()) {
			case "++" : op = UnaryOperator.UnaryOp.SUF_INC; break;
			case "--" : op = UnaryOperator.UnaryOp.SUF_DEC; break;
			default : throw new InternalError("Invalid token " + ctx.op.getText());
		}
		rst.put(ctx, new SuffixOperator(op, getExpr(ctx.expression())));
	}

	@Override public void exitPrefixExpression(MxllParser.PrefixExpressionContext ctx) {
		UnaryOperator.UnaryOp op;
		switch (ctx.op.getText()) {
			case "+" : op = UnaryOperator.UnaryOp.PLUS; break;
			case "-" : op = UnaryOperator.UnaryOp.MINUS; break;
			case "++" : op = UnaryOperator.UnaryOp.PRE_INC; break;
			case "--" : op = UnaryOperator.UnaryOp.PRE_DEC; break;
			case "!" : op = UnaryOperator.UnaryOp.LOGIC_NOT; break;
			case "~" : op = UnaryOperator.UnaryOp.BIT_NOT; break;
			default : throw new InternalError("Invalid token " + ctx.op.getText());
		}
		rst.put(ctx, new PrefixOperator(op, getExpr(ctx.expression())));
	}

	@Override public void exitBinaryExpression(MxllParser.BinaryExpressionContext ctx) {
		BinaryOperator.BinaryOp op;
		switch (ctx.op.getText()) {
			case "*" : op = BinaryOperator.BinaryOp.MUL; break;
			case "/" : op = BinaryOperator.BinaryOp.DIV; break;
			case "%" : op = BinaryOperator.BinaryOp.MOD; break;
			case "+" : op = BinaryOperator.BinaryOp.ADD; break;
			case "-" : op = BinaryOperator.BinaryOp.SUB; break;
			case "<" : op = BinaryOperator.BinaryOp.LT; break;
			case ">" : op = BinaryOperator.BinaryOp.GT; break;
			case "<=" : op = BinaryOperator.BinaryOp.LE; break;
			case ">=" : op = BinaryOperator.BinaryOp.GE; break;
			case "==" : op = BinaryOperator.BinaryOp.EQ; break;
			case "!=" : op = BinaryOperator.BinaryOp.NE; break;
			case "<<" : op = BinaryOperator.BinaryOp.LSHIFT; break;
			case ">>" : op = BinaryOperator.BinaryOp.RSHIFT; break;
			case "&" : op = BinaryOperator.BinaryOp.BIT_AND; break;
			case "|" : op = BinaryOperator.BinaryOp.BIT_OR; break;
			case "^" : op = BinaryOperator.BinaryOp.BIT_XOR; break;
			case "&&" : op = BinaryOperator.BinaryOp.LOGIC_AND; break;
			case "||" : op = BinaryOperator.BinaryOp.LOGIC_OR; break;
			default : throw new InternalError("Invalid token " + ctx.op.getText());
		}
		rst.put(ctx, new BinaryOperator(getExpr(ctx.expression(0)), op, getExpr(ctx.expression(1))));
	}

	@Override public void exitAssignmentExpression(MxllParser.AssignmentExpressionContext ctx) {
		rst.put(ctx, new Assign(getExpr(ctx.expression(0)), getExpr(ctx.expression(1))));
	}

	@Override public void exitVoidType(MxllParser.VoidTypeContext ctx) {
		rst.put(ctx, new VoidType());
	}
	@Override public void exitBoolType(MxllParser.BoolTypeContext ctx) {
		rst.put(ctx, new BoolType());
	}
	@Override public void exitIntType(MxllParser.IntTypeContext ctx) {
		rst.put(ctx, new IntegerType());
	}
	@Override public void exitStringType(MxllParser.StringTypeContext ctx) {
		rst.put(ctx, new StringType());
	}
	@Override public void exitClassType(MxllParser.ClassTypeContext ctx) {
		rst.put(ctx, new ClassType(ctx.Identifier().getText()));
	}
	@Override public void exitArrayType(MxllParser.ArrayTypeContext ctx) {
		rst.put(ctx, new ArrayType((Type)rst.get(ctx.type()), 1));
	}

	@Override public void exitBoolConstant(MxllParser.BoolConstantContext ctx) {
		rst.put(ctx, new BoolConst(new Location(ctx), ctx.getText().equals("true")));
	}
	@Override public void exitIntConstant(MxllParser.IntConstantContext ctx) {
		rst.put(ctx, new IntegerConst(new Location(ctx), Long.parseLong(ctx.INTEGER().getText())));
	}
	@Override public void exitStringConstant(MxllParser.StringConstantContext ctx) {
		String value = ctx.STRING().getText();
		value = value.substring(1, value.length() - 1);
		rst.put(ctx, new StringConst(new Location(ctx), value));
	}
	@Override public void exitNullConstant(MxllParser.NullConstantContext ctx) {
		rst.put(ctx, new VariableNode(new Location(ctx), "null"));
	}

	private List<Statement> getStmts(MxllParser.StatementContext ctx) {
		if (ctx == null) return null;
		else return (List<Statement>)rst.get(ctx);
	}

	private Statement getStmt(MxllParser.StatementContext ctx) {
		if (ctx == null) return null;
		else return (Statement)rst.get(ctx);
	}

	private Expression getExpr(MxllParser.ExpressionContext ctx) {
		if (ctx == null) return null;
		else return (Expression)rst.get(ctx);
	}
}
