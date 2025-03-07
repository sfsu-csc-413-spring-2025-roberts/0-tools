package tools.readers.lines;

import java.util.List;

import tools.grammar.Grammar;
import tools.readers.GrammarReader;
import tools.readers.GrammarReader.Modes;

public class ModeReader extends LineReader {

    public ModeReader(Grammar grammar, String line, GrammarReader reader) {
        super(grammar, line, reader);
    }

    @Override
    public void read() throws Exception {
        String currentMode = this.line.replace("#", "").trim();

        List<Modes> matches = List.of(Modes.values()).stream()
                .filter(mode -> mode.toString().toLowerCase().equals(currentMode.toLowerCase())).toList();

        if (matches.size() != 1) {
            throw new Exception(String.format("%s mode not found", currentMode));
        } else {
            this.reader.setMode(matches.get(0));
        }
    }

}
