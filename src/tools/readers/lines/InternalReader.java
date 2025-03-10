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
        String internalName = String.format("%s%s", internal.substring(0, 1).toUpperCase(), internal.substring(1));

        this.grammar.addInternal(new SymbolicConstant(String.format("{%s}", internalName), internalName));
    }

}
