package tools.grammar;

public class SymbolicConstant {
    private String lexeme;
    private String constantName;

    public SymbolicConstant(String lexeme, String constantName) {
        this.lexeme = lexeme;
        this.constantName = constantName;
    }

    public String getLexeme() {
        return this.lexeme;
    }

    public String getConstantName() {
        return this.constantName;
    }
}
