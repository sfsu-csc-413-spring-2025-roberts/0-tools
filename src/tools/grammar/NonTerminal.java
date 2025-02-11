package tools.grammar;

public class NonTerminal extends Node {
    private String name;

    public NonTerminal(Node parent, String name) {
        super(parent);

        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAstNode() {
        return this.children.stream().filter(child -> child instanceof Terminal).count() > 0;
    }
}
