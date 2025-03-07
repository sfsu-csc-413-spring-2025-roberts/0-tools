package tools.readers.lines;

import tools.grammar.Grammar;
import tools.grammar.SymbolicConstant;
import tools.readers.GrammarReader;

public class InternalReader extends LineReader {

    public InternalReader(Grammar grammar, String line, GrammarReader reader) {
        super(grammar, line, reader);
    }

    @Override
    public void read() throws Exception {
        String internal = this.line.trim();

        this.grammar.addInternal(new SymbolicConstant(String.format("{%s}", internal), internal.toUpperCase()));
    }

}
