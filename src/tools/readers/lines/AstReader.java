package tools.readers.lines;

import tools.grammar.Grammar;
import tools.readers.GrammarReader;

public class AstReader extends LineReader {

    public AstReader(Grammar grammar, String line, GrammarReader reader) {
        super(grammar, line, reader);
    }

    @Override
    public void read() throws Exception {
        String ast = this.line.trim();

        this.grammar.addAst(String.format("%sTree", ast));
    }

}
