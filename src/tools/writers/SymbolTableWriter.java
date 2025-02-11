package tools.writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import tools.grammar.Grammar;
import tools.grammar.SymbolicConstant;

public class SymbolTableWriter extends AutoGeneratedFileWriter {

    private File file;

    public SymbolTableWriter(Grammar grammar) {
        super(grammar);

        this.file = new File("src/lexer/daos/SymbolTable.java");
    }

    @Override
    public void write() throws Exception {
        Files.createDirectories(Path.of(this.file.getParent()));
        if (file.exists()) {
            file.delete();
        }

        try (FileWriter writer = new FileWriter(file)) {

            writer.write(this.formattedLine("package lexer.daos;", 0, 2));

            writer.write(this.formattedLine("import java.util.Map;", 0));
            writer.write(this.formattedLine("import java.util.HashMap;", 0, 2));
            writer.write(this.getAutoGeneratedComment());

            writer.write(this.formattedLine("public class SymbolTable {", 0, 1));
            writer.write(this.formattedLine("private static Map<String, Symbol> symbols;", 1, 2));

            writer.write(this.formattedLine("static {", 1, 1));
            writer.write(this.formattedLine("symbols = new HashMap<>();", 2, 2));

            for (String keyword : this.grammar.getKeywords()) {
                this.writeTableEntry(writer, this.grammar.getSymbolicConstant(keyword));
            }
            for (String operator : this.grammar.getOperators()) {
                this.writeTableEntry(writer, this.grammar.getSymbolicConstant(operator));
            }
            for (String separator : this.grammar.getSeparators()) {
                this.writeTableEntry(writer, this.grammar.getSymbolicConstant(separator));
            }

            writer.write(this.formattedLine("}", 1, 2));

            this.writeLookupMethod(writer);

            writer.write(this.formattedLine("}", 0, 1));
            writer.flush();
            writer.close();
        }
    }

    private void writeTableEntry(FileWriter writer, SymbolicConstant constant) throws IOException {
        String line = String.format("symbols.put(\"%s\", new Symbol(TokenKind.%s, \"%s\"));", constant.getLexeme(),
                constant.getConstantName(), constant.getLexeme());

        writer.write(this.formattedLine(line, 2));
    }

    private void writeLookupMethod(FileWriter writer) throws IOException {
        writer.write(this.formattedLine("public static Symbol symbol(String lexeme, TokenKind kind) {", 1, 1));
        writer.write(this.formattedLine("Symbol s = symbols.get(lexeme);", 2, 2));

        writer.write(this.formattedLine("if (s == null) {", 2, 1));
        writer.write(this.formattedLine("if (kind == TokenKind.BogusToken) {", 3, 1));
        writer.write(this.formattedLine("// bogus string so don't enter into symbol map", 4, 1));
        writer.write(this.formattedLine("return null;", 4, 1));
        writer.write(this.formattedLine("}", 3, 2));
        writer.write(this.formattedLine("s = new Symbol(lexeme, kind);", 3, 1));
        writer.write(this.formattedLine("symbols.put(lexeme, s);", 3, 1));
        writer.write(this.formattedLine("}", 2, 2));
        writer.write(this.formattedLine("return s;", 2, 1));
        writer.write(this.formattedLine("}", 1, 1));

    }

}
