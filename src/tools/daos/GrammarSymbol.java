package tools.daos;

public class GrammarSymbol {
    private String lexeme;
    private String constantName;

    public GrammarSymbol(String lexeme, String constantName) {
        this.lexeme = lexeme;
        this.constantName = constantName;
    }

    public String getLexeme() {
        return this.lexeme;
    }

    public String getConstantName() {
        return this.constantName;
    }

    @Override
    public String toString() {
        return String.format("%-15s %s", this.lexeme, this.constantName);
    }
}
