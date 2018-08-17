package My;

import My.FrontEnd.AST;
import My.FrontEnd.ASTBuilder;
import My.FrontEnd.ParserErrorListener;
import My.Parser.MxllLexer;
import My.Parser.MxllParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main {
	public static void main(String[] args) throws Exception {
		try {
			if (args.length > 0)
				compile(new FileInputStream(args[0]));
			else
				compile(System.in);
		} catch (My.Utility.SemanticError e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (My.Utility.InternalError e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void compile(InputStream sourceCode) throws Exception {
		ANTLRInputStream input = new ANTLRInputStream(sourceCode);
		MxllLexer lexer = new MxllLexer(input);
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	    MxllParser parser = new MxllParser(tokens);
	    parser.removeErrorListeners();
	    parser.addErrorListener(new ParserErrorListener());
	    ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        ASTBuilder listener = new ASTBuilder();
		walker.walk(listener, tree);

		AST ast = listener.getAST();
		ast.resolveSymbol();
		ast.checkType();
	}
}
