package tools.readers;

import java.io.IOException;
import java.nio.file.Path;

import tools.daos.Grammar;
import tools.daos.SymbolicConstants;

public class GrammarReader extends ToolFileReader {
    private Grammar grammar;
    private SymbolicConstants symbolicConstants;

    public GrammarReader(Path grammarFile, Path symbolicConstantsFile) throws IOException {
        super(grammarFile);

        SymbolicConstantsReader reader = new SymbolicConstantsReader(symbolicConstantsFile);
        reader.read();
        this.symbolicConstants = reader.getConstants();

        this.grammar = new Grammar(symbolicConstants);
    }

    @Override
    public void read() {

        while (this.hasNext()) {
            String[] line = this.next().split("::=");

            String nonTerminal = line[0].trim();
            String[] rightHandSideRules = line[1].trim().split(" \\| ");

            this.grammar.addNonTerminal(nonTerminal);

            for (String rule : rightHandSideRules) {
                this.grammar.addProduction(nonTerminal, rule);
            }
        }
    }
}
