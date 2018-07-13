package My.Utility;

import My.AST.Location;

public class InternalError extends Error {
	public InternalError(String message) {
        super(message);
    }

	public InternalError(Location loc, String message) {
        super(loc.toString() + message);
    }
}
