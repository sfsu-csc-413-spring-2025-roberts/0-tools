package tools.grammar;

import java.util.ArrayList;
import java.util.List;

public class Grammar {

    private List<SymbolicConstant> allConstants;
    private List<String> allAsts;

    private List<SymbolicConstant> keywords;
    private List<SymbolicConstant> types;
    private List<SymbolicConstant> operators;
    private List<SymbolicConstant> separators;
    private List<SymbolicConstant> internals;
    private List<String> asts;
    private List<String> astsWithTokens;

    public Grammar() {
        this.allConstants = new ArrayList<>();
        this.allAsts = new ArrayList<>();
        this.keywords = new ArrayList<>();
        this.types = new ArrayList<>();
        this.operators = new ArrayList<>();
        this.separators = new ArrayList<>();
        this.internals = new ArrayList<>();
        this.asts = new ArrayList<>();
        this.astsWithTokens = new ArrayList<>();
    }

    public void addKeyword(SymbolicConstant keyword) {
        this.keywords.add(keyword);
        this.allConstants.add(keyword);
    }

    public List<SymbolicConstant> getKeywords() {
        return List.copyOf(this.keywords);
    }

    public void addType(SymbolicConstant type) {
        this.types.add(type);
        this.allConstants.add(type);
    }

    public List<SymbolicConstant> getTypes() {
        return List.copyOf(this.types);
    }

    public void addOperator(SymbolicConstant operator) {
        this.operators.add(operator);
        this.allConstants.add(operator);
    }

    public List<SymbolicConstant> getOperators() {
        return List.copyOf(this.operators);
    }

    public void addSeparator(SymbolicConstant separator) {
        this.separators.add(separator);
        this.allConstants.add(separator);
    }

    public List<SymbolicConstant> getSeparators() {
        return List.copyOf(this.separators);
    }

    public void addInternal(SymbolicConstant internal) {
        this.internals.add(internal);
        this.allConstants.add(internal);
    }

    public List<SymbolicConstant> getInternals() {
        return List.copyOf(this.internals);
    }

    public List<SymbolicConstant> getAllSymbolicConstants() {
        return List.copyOf(this.allConstants);
    }

    public void addAst(String ast) {
        this.asts.add(ast);
        this.allAsts.add(ast);
    }

    public List<String> getAsts() {
        return List.copyOf(this.asts);
    }

    public void addAstWithToken(String ast) {
        this.astsWithTokens.add(ast);
        this.allAsts.add(ast);
    }

    public List<String> getAstsWithTokens() {
        return List.copyOf(this.astsWithTokens);
    }

    public List<String> getAllAsts() {
        return List.copyOf(this.allAsts);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(String.format("### Token (%d) ###%s", this.allConstants.size(), System.lineSeparator()));
        buffer.append(String.format("Keywords (%d):%s    %s%s", this.keywords.size(), System.lineSeparator(),
                String.join(", ", this.keywords.stream()
                        .map(c -> String.format("%s ('%s')", c.getConstantName(), c.getLexeme())).toList()),
                System.lineSeparator()));
        buffer.append(String.format("Types (%d):%s    %s%s", this.types.size(), System.lineSeparator(),
                String.join(", ", this.types.stream()
                        .map(c -> String.format("%s ('%s')", c.getConstantName(), c.getLexeme())).toList()),
                System.lineSeparator()));
        buffer.append(String.format("Operators (%d):%s    %s%s", this.operators.size(), System.lineSeparator(),
                String.join(", ", this.operators.stream()
                        .map(c -> String.format("%s ('%s')", c.getConstantName(), c.getLexeme())).toList()),
                System.lineSeparator()));
        buffer.append(String.format("Separators (%d):%s    %s%s", this.separators.size(), System.lineSeparator(),
                String.join(", ", this.separators.stream()
                        .map(c -> String.format("%s ('%s')", c.getConstantName(), c.getLexeme())).toList()),
                System.lineSeparator()));
        buffer.append(String.format("Internals (%d):%s    %s%s", this.internals.size(), System.lineSeparator(),
                String.join(", ", this.internals.stream()
                        .map(c -> String.format("%s ('%s')", c.getConstantName(), c.getLexeme())).toList()),
                System.lineSeparator()));

        buffer.append(String.format("%s### ASTs (%d) ###%s", System.lineSeparator(), this.allAsts.size(),
                System.lineSeparator()));
        buffer.append(String.format("No tokens (%d):%s    %s%s", this.asts.size(), System.lineSeparator(),
                String.join(", ", this.asts), System.lineSeparator()));
        buffer.append(String.format("With tokens (%d):%s    %s%s", this.astsWithTokens.size(), System.lineSeparator(),
                String.join(", ", this.astsWithTokens), System.lineSeparator()));

        return buffer.toString();
    }
}
