package My.Entity;

import My.AST.Location;
import My.AST.Statement.FunctionDefinition;
import My.AST.Statement.*;
import My.Type.ClassType;

import java.util.List;

public class ClassEntity extends Entity {
	private List<VariableDefinition> memberVars;
	private List<FunctionDefinition> memberFuncs;
	private Scope scope;
	private FunctionEntity constructor;
	private int size;

	public ClassEntity(Location loc, String name, List<VariableDefinition> memberVars, List<FunctionDefinition> memberFuncs) {
		super(loc, new ClassType(name), name);
		this.memberVars = memberVars;
		this.memberFuncs = memberFuncs;
		this.scope = null;
		this.constructor = null;
		((ClassType)this.type).setEntity(this);
	}

	public List<VariableDefinition> memberVars() {
        return memberVars;
    }

    public List<FunctionDefinition> memberFuncs() {
        return memberFuncs;
    }

	public Scope scope() {
        return scope;
    }
    public void setScope(Scope scope) {
        this.scope = scope;
    }

	public FunctionEntity constructor() {
        return constructor;
    }
    public void setConstructor(FunctionEntity constructor) {
        this.constructor = constructor;
    }

	@Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "class entity : " + name;
    }
}
