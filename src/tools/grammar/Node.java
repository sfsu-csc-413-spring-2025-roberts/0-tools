package tools.grammar;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
    protected Node parent;
    protected List<Node> children;

    public Node(Node parent) {
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public Node getParent() {
        return this.parent;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public List<Node> getChildren() {
        return this.children;
    }
}
