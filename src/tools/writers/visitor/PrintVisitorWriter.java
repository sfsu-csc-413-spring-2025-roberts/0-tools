package tools.writers.visitor;

import org.apache.velocity.app.VelocityEngine;

import tools.grammar.Grammar;
import tools.writers.BaseWriterWithClassName;

public class PrintVisitorWriter extends BaseWriterWithClassName {

    public PrintVisitorWriter(Grammar grammar, String className) {
        super(grammar, className);
    }

    @Override
    public void write(VelocityEngine engine, String templateFile) {
    }

}
