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
        return this.formattedLine(String.format("public class %s extends AST implements TokenTree {", className), 0,
                1);
    }

    @Override
    protected void insertImports(FileWriter writer, String className) throws Exception {
        writer.write(this.formattedLine("import lexer.daos.Token;", 0, 1));
        writer.write(this.formattedLine("import ast.TokenTree;", 0, 2));
    }

    @Override
    protected void insertMembers(FileWriter writer, String className) throws Exception {
        writer.write(this.formattedLine("private Token token;", 1, 2));

        writer.write(this.formattedLine(String.format("public %s(Token token) {", className), 1, 1));
        writer.write(this.formattedLine("this.token = token;", 2, 1));
        writer.write(this.formattedLine("}", 1, 2));
    }

    @Override
    protected void insertMethods(FileWriter writer, String className) throws Exception {
        writer.write(this.formattedLine("@Override", 1, 1));
        writer.write(this.formattedLine("public Token getToken() {", 1, 1));
        writer.write(this.formattedLine("return this.token;", 2, 1));
        writer.write(this.formattedLine("}", 1, 1));
    }

}
