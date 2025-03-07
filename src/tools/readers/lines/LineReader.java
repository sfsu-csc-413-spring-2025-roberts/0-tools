package tools.readers.lines;

import tools.grammar.Grammar;
import tools.readers.GrammarReader;

public abstract class LineReader {

    public static LineReader get(Grammar grammar, String line, GrammarReader reader) {
        if (line.startsWith("#")) {
            return new ModeReader(grammar, line, reader);
        }

        switch (reader.getMode()) {
            case INIT:
                System.err.println("A mode was not set in the reader before attempting to read a line.");
                System.exit(1);
                break;
            case KEYWORDS:
                return new KeywordReader(grammar, line, reader);
            case TYPES:
                return new TypeReader(grammar, line, reader);
            case OPERATORS:
                return new OperatorReader(grammar, line, reader);
            case SEPARATORS:
                return new SeparatorReader(grammar, line, reader);
            case INTERNALS:
                return new InternalReader(grammar, line, reader);
            case ASTS:
                return new AstReader(grammar, line, reader);
            case ASTS_WITH_TOKENS:
                return new AstWithTokenReader(grammar, line, reader);
            default:
                System.err.println("No mode match was found.");
                System.exit(1);
                break;
        }

        // Should not get here
        return null;
    }

    protected String line;
    protected Grammar grammar;
    protected GrammarReader reader;

    public LineReader(Grammar grammar, String line, GrammarReader reader) {
        this.grammar = grammar;
        this.line = line;
        this.reader = reader;
    }

    public abstract void read() throws Exception;
}
