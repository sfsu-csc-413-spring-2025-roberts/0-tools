package tools.writers;

import java.io.FileWriter;
import java.util.List;

import tools.grammar.Grammar;

public class AstNodeWithSymbolWriter extends AstNodeWriter {

    public AstNodeWithSymbolWriter(Grammar grammar) {
        super(grammar);
    }

    @Override
    protected List<String> getNodes() {
        return this.grammar.getAstsWithSymbols();
    }

    protected String getClassDeclarationLine(String className) {
        return this.formattedLine(String.format("public class %s extends AST implements SymbolTree {", className), 0,
                1);
    }

    @Override
    protected void insertImports(FileWriter writer, String className) throws Exception {
        writer.write(this.formattedLine("import lexer.daos.Symbol;", 0, 1));
        writer.write(this.formattedLine("import ast.SymbolTree;", 0, 2));
    }

    @Override
    protected void insertMembers(FileWriter writer, String className) throws Exception {
        writer.write(this.formattedLine("private Symbol symbol;", 1, 2));

        writer.write(this.formattedLine(String.format("public %s(Symbol symbol) {", className), 1, 1));
        writer.write(this.formattedLine("this.symbol = symbol;", 2, 1));
        writer.write(this.formattedLine("}", 1, 2));
    }

    @Override
    protected void insertMethods(FileWriter writer, String className) throws Exception {
        writer.write(this.formattedLine("public Symbol getSymbol() {", 1, 1));
        writer.write(this.formattedLine("return this.symbol;", 2, 1));
        writer.write(this.formattedLine("}", 1, 1));
    }

}
