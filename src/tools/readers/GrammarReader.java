package tools.readers;

import java.io.IOException;
import java.nio.file.Path;

import tools.daos.Grammar;
import tools.daos.SymbolicConstants;

public class GrammarReader extends ToolFileReader<Grammar> {
    private Grammar grammar;
    private SymbolicConstants symbolicConstants;

    public GrammarReader(Path grammarFile, Path symbolicConstantsFile) throws IOException {
        super(grammarFile);

        this.symbolicConstants = new SymbolicConstantsReader(symbolicConstantsFile).read();

        this.grammar = new Grammar(symbolicConstants);
    }

    @Override
    public Grammar read() {
        while (this.hasNext()) {
            String[] line = this.next().split("::=");

            String nonTerminal = line[0].trim();
            String[] rightHandSideRules = line[1].trim().split(" \\| ");

            this.grammar.addNonTerminal(nonTerminal);

            for (String rule : rightHandSideRules) {
                this.grammar.addProduction(nonTerminal, rule);
            }
        }

        return this.grammar;
    }

    public Grammar getGrammar() {
        return this.grammar;
    }
}
