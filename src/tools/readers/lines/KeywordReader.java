package tools.readers.lines;

import tools.grammar.Grammar;
import tools.grammar.SymbolicConstant;
import tools.readers.GrammarReader;

public class KeywordReader extends LineReader {

    public KeywordReader(Grammar grammar, String line, GrammarReader reader) {
        super(grammar, line, reader);
    }

    @Override
    public void read() throws Exception {
        String keyword = this.line.trim();
        String constant = String.format("%s%s", this.line.substring(0, 1).toUpperCase(), this.line.substring(1));

        this.grammar.addKeyword(new SymbolicConstant(keyword, constant));
    }

}
