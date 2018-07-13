package My.FrontEnd;

import My.AST.Expression.*;
import My.AST.Statement.*;
import My.AST.*;
import My.Entity.*;
import My.Type.*;
import My.Utility.SemanticError;
import My.Utility.InternalError;

import java.util.List;

import static My.AST.Expression.UnaryOperator.UnaryOp.*;

public class TypeChecker extends Visitor {
	static final Type boolType = new BoolType();
	static final Type integerType = new IntegerType();
	static final Type stringType = new StringType();

	private int loopDepth = 0;
	private FunctionEntity currentFunction = null;
	private Scope scope;

	public TypeChecker(Scope scope) {
		this.scope = scope;
	}

	static private void checkCompatibility(Location loc, Type real, Type expect) {
		if (!real.isCompatible(expect))
			throw new SemanticError(loc, "Incompatible Type : " + real.toString() + " and " + expect.toString());
	}

	@Override
	public Void visit(FunctionDefinition node) {
		currentFunction = node.entity();
		if (!currentFunction.isConstructor() && currentFunction.returnType() == null)
			throw new SemanticError(node.location(), "function has no return type");
		visitStatement(node.entity().body());
		currentFunction = null;
		return null;
	}

	@Override
	public Void visit(VariableDefinition node) {
		Expression init = node.entity().init();
		if (init != null) {
			visitExpression(init);
			checkCompatibility(node.location(), init.type(), node.entity().type());
		}
		if (node.entity().type().isVoid())
			throw new SemanticError(node.location(), "Cannot set void type for variable");
		return null;
	}

	@Override
	public Void visit(IfStatement node) {
		visitExpression(node.cond());
		checkCompatibility(node.location(), node.cond().type(), boolType);
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
		if (node.cond() != null) {
			visitExpression(node.cond());
			checkCompatibility(node.location(), node.cond().type(), boolType);
		}
		if (node.step() != null)
			visitExpression(node.step());
		if (node.body() != null) {
			loopDepth++;
			visitStatement(node.body());
			loopDepth--;
		}
		return null;
	}

	@Override
	public Void visit(WhileStatement node) {
		visitExpression(node.cond());
		checkCompatibility(node.location(), node.cond().type(), boolType);
		if (node.body() != null) {
			loopDepth++;
			visitStatement(node.body());
			loopDepth--;
		}
		return null;
	}

	@Override
	public Void visit(BreakStatement node) {
		if (loopDepth <= 0)
			throw new SemanticError(node.location(), "unexpected break");
		return null;
	}

	@Override
	public Void visit(ContinueStatement node) {
		if (loopDepth <= 0)
			throw new SemanticError(node.location(), "unexpected continue");
		return null;
	}

	@Override
	public Void visit(ReturnStatement node) {
		if (currentFunction == null)
			throw new SemanticError(node.location(), "unexpected return");
		if (currentFunction.isConstructor()) {
			if (node.expr() != null)
				throw new SemanticError(node.location(), "cannot return in constructor");
		} else {
			if (node.expr() != null) {
				visitExpression(node.expr());
				checkCompatibility(node.location(), node.expr().type(), currentFunction.returnType());
			} else {
				if (!currentFunction.returnType().isVoid())
					throw new SemanticError(node.location(), "connot return in void function");
			}
		}
		return null;
	}

	@Override
	public Void visit(Assign node) {
		visitExpression(node.lhs());
		if (!node.lhs().isAssignable())
			throw new SemanticError(node.location(), "LHS is not assignable");
		visitExpression(node.rhs());
		checkCompatibility(node.location(), node.lhs().type(), node.rhs().type());
		return null;
	}

	@Override
	public Void visit(FunctionCall node) {
		visitExpression(node.expr());
		Type funcType = node.expr().type();
		if (!funcType.isFunction())
			throw new SemanticError(node.location(), "Invalid FunctionCall");
		FunctionEntity entity = ((FunctionType)funcType).entity();

		List<ParameterEntity> params = entity.params();
		List<Expression> args = node.args();
		int base = 0; // count for this pointer
		if (node.expr() instanceof MemberNode ||
			(node.expr() instanceof VariableNode && ((VariableNode)node.expr()).isMember()))
			base = 1;

		if (params.size() - base != args.size())
			throw new SemanticError(node.location(), "Wrong parameters number in FunctionCall");
		for (int i = 0; i < args.size(); i++) {
			ParameterEntity param = params.get(i + base);
			Expression expr = args.get(i);
			visitExpression(expr);
			checkCompatibility(expr.location(), expr.type(), param.type());
		}

		if (node.args() != null)
			visitExpressions(node.args());
		return null;
	}

	@Override
	public Void visit(NewExpression node) {
		if (node.exprs() != null) {
			for (Expression expr : node.exprs()) {
				visitExpression(expr);
				checkCompatibility(expr.location(), expr.type(), integerType);
			}
		}
		return null;
	}

	@Override
	public Void visit(SubscriptNode node) {
		visitExpression(node.expr());
		visitExpression(node.index());
		if (!node.expr().type().isArray())
			throw new SemanticError(node.location(), "Invalid subscript statement");
		checkCompatibility(node.index().location(), node.index().type(), integerType);
		node.setType(((ArrayType)(node.expr().type())).baseType());
		return null;
	}

	@Override
	public Void visit(MemberNode node) {
		visitExpression(node.expr());
		Type type = node.expr().type();
		Entity member;
		if (type.isClass()){
			member = ((ClassType)type).entity().scope().lookupCurrentLevel(node.member());
		} else if (type.isArray()) {
			member = ArrayType.scope().lookupCurrentLevel(node.member());
		} else if (type.isString()) {
			member = StringType.scope().lookupCurrentLevel(node.member());
		} else throw new SemanticError(node.location(), "Invalid get member operation");
		if (member == null)
			throw new SemanticError(node.location(), "Cannot resolve member : " + node.member().toString());
		node.setEntity(member);
		node.setType(member.type());
		return null;
	}

	@Override
	public Void visit(UnaryOperator node) {
		visitExpression(node.expr());
		Type type;
		switch (node.operator()) {
			case PRE_INC: case PRE_DEC: case SUF_INC: case SUF_DEC:
			case MINUS: case PLUS: case BIT_NOT: type = integerType; break;
			case LOGIC_NOT: type = boolType; break;
			default: throw new InternalError("Invalid operator" + node.operator());
		}
		checkCompatibility(node.location(), node.expr().type(), type);
		return null;
	}

	@Override
	public Void visit(PrefixOperator node) {
		visit((UnaryOperator)node);
		if (node.operator() == PRE_INC || node.operator() == PRE_DEC)
			node.setAssignable(true);
        if (!node.expr().isAssignable())
            throw new SemanticError(node.location(), "lvalue is needed");
		return null;
	}

	@Override
	public Void visit(SuffixOperator node) {
		visit((UnaryOperator)node);
        if (!node.expr().isAssignable())
            throw new SemanticError(node.location(), "lvalue is needed");
		return null;
	}

	@Override
	public Void visit(BinaryOperator node) {
		visitExpression(node.left());
		visitExpression(node.right());
		Type ltype = node.left().type(), rtype = node.right().type();
		switch(node.operator()) {
			case SUB: case MUL: case DIV: case MOD:
			case LSHIFT: case RSHIFT:
			case BIT_AND: case BIT_XOR: case BIT_OR:
				checkCompatibility(node.left().location(), ltype, integerType);
				checkCompatibility(node.right().location(), rtype, integerType);
				node.setType(ltype);
				break;
			case LT: case GT: case LE: case GE:
				checkCompatibility(node.location(), ltype, rtype);
				if (!ltype.isFullComparable() && !rtype.isFullComparable())
					throw new SemanticError(node.location(), "Cannot be compared");
				node.setType(boolType);
				break;
			case EQ: case NE:
				checkCompatibility(node.location(), ltype, rtype);
				if (!ltype.isHalfComparable() && !rtype.isHalfComparable())
					throw new SemanticError(node.location(), "Cannot be compared");
				node.setType(boolType);
				break;
			case LOGIC_AND: case LOGIC_OR:
				checkCompatibility(node.left().location(), ltype, boolType);
				checkCompatibility(node.right().location(), rtype, boolType);
				node.setType(boolType);
				break;
			case ADD:
				checkCompatibility(node.location(), ltype, rtype);
				if (!ltype.isInteger() && !ltype.isString())
					throw new SemanticError(node.location(), "Cannot be added");
				node.setType(ltype);
				break;
			default:
				throw new InternalError("Invalid operator " + node.operator());
		}
		return null;
	}
}
