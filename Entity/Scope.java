package My.Entity;

import My.Utility.SemanticError;

import java.util.*;

public class Scope {
	private Map<String, Entity> entities = new HashMap<>();
	private List<Scope> children = new ArrayList<>();
	private Scope parent;
	private boolean isTopLevel;

	public Scope(boolean isTopLevel) {
		this.isTopLevel = isTopLevel;
	}

	public Scope(Scope parent) {
		this.parent = parent;
		this.isTopLevel = (parent == null);
		if (this.parent != null)
			this.parent.addChildren(this);
	}

	public void insert(Entity entity) {
		if (entities.containsKey(entity.name()))
			throw new SemanticError(entity.location(), "duplicated symbol : " + entity.name());
		entities.put(entity.name(), entity);
	}

	// search in entire symbol table
	public Entity lookup(String name) {
		Entity entity = entities.get(name);
		if (entity == null)
			return isTopLevel ? null : parent.lookup(name);
		else
			return entity;
	}
	// only search in current level
    public Entity lookupCurrentLevel(String name) {
        return entities.get(name);
    }

	public Map<String, Entity> entities() {
        return entities;
    }

	public List<Scope> children() {
        return children;
    }
    public void addChildren(Scope s) {
        children.add(s);
    }

	public Scope parent() {
        return isTopLevel ? null : parent;
    }

    public boolean isTopLevel() {
        return isTopLevel;
    }

	// all variable entities
	public List<VariableEntity> allLocalVariables() {
		List<VariableEntity> ret = new LinkedList<>();
		for (Entity entity : entities.values()) {
			if (entity instanceof VariableEntity) {
				ret.add((VariableEntity)entity);
			}
		}
		for (Scope child : children)
			ret.addAll(child.allLocalVariables());
		return ret;
	}
}
