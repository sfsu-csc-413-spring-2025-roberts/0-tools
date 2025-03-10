package tools.readers.lines;

import tools.grammar.Grammar;
import tools.grammar.SymbolicConstant;
import tools.readers.GrammarReader;

public class TypeReader extends LineReader {

    public TypeReader(Grammar grammar, String line, GrammarReader reader) {
        super(grammar, line, reader);
    }

    @Override
    public void read() throws Exception {
        String type = this.line.trim();
        String typeConstant = String.format("%s%s", type.substring(0, 1).toUpperCase(), type.substring(1));

        this.grammar.addType(new SymbolicConstant(type, String.format("%sType", typeConstant)));
        this.grammar.addType(new SymbolicConstant(String.format("<%s>", type), String.format("%sLit", typeConstant)));
    }
}
