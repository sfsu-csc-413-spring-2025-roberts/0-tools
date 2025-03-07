package tools.writers;

import tools.grammar.Grammar;

public abstract class BaseWriterWithClassName extends BaseWriter {

    protected String className;

    public BaseWriterWithClassName(Grammar grammar, String className) {
        super(grammar);
        this.className = className;
    }
}
