package tools.readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import tools.grammar.Grammar;
import tools.readers.lines.LineReader;

public class GrammarReader {

    private List<String> fileLines;
    private Modes mode;
    private boolean debug;

    public enum Modes {
        KEYWORDS, TYPES, OPERATORS, SEPARATORS, INTERNALS, ASTS, ASTS_WITH_TOKENS, INIT
    }

    public GrammarReader(Path path) throws IOException {
        this(path, false);
    }

    public GrammarReader(Path path, boolean debug) throws IOException {
        this.fileLines = Files.readAllLines(path).stream().filter(line -> line.trim().length() != 0).toList();
        this.mode = Modes.INIT;
        this.debug = debug;
    }

    public Grammar read() throws Exception {
        Grammar grammar = new Grammar();

        for (String line : fileLines) {
            if (this.isDebugging()) {
                System.out.println(String.format("LINE: %s", line));
            }

            LineReader.get(grammar, line, this).read();
        }

        return grammar;
    }

    public void setMode(Modes mode) {
        this.mode = mode;
    }

    public Modes getMode() {
        return this.mode;
    }

    public boolean isDebugging() {
        return this.debug;
    }
}
