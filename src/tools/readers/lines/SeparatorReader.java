package tools.readers.lines;

import tools.grammar.Grammar;
import tools.grammar.SymbolicConstant;
import tools.readers.GrammarReader;

public class SeparatorReader extends LineReader {

    public SeparatorReader(Grammar grammar, String line, GrammarReader reader) {
        super(grammar, line, reader);
    }

    @Override
    public void read() throws Exception {
        String[] lineParts = this.line.split("\\s+");

        this.grammar.addSeparator(new SymbolicConstant(lineParts[0].trim(), lineParts[1].trim()));
    }

}
