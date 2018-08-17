package My.FrontEnd;

import My.AST.Location;
import My.Utility.SemanticError;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ParserErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object symbol, int row, int column, String message, RecognitionException e) {
        throw new SemanticError(new Location(row, column), message);
    }
}
