package tools.grammar;

public class Terminal extends Node {
    public enum TerminalType {
        KEYWORD,
        TYPE,
        OPERATOR
    }

    private String lexeme;
    private String symbolicConstant;
    private TerminalType type;

    public Terminal(Node parent, String lexeme, TerminalType type) {
        this(parent, lexeme, type, String.format("%s%s", lexeme.substring(0, 1).toUpperCase(), lexeme.substring(1)));
    }

    public Terminal(Node parent, String lexeme, TerminalType type, String symbolicConstant) {
        super(parent);

        this.lexeme = lexeme;
        this.symbolicConstant = symbolicConstant;
    }

    public String getLexeme() {
        return this.lexeme;
    }

    public String getSymbolicConstant() {
        return this.symbolicConstant;
    }

    public TerminalType getType() {
        return this.type;
    }
}
