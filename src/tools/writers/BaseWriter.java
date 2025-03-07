package tools.writers;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.grammar.Grammar;

public abstract class BaseWriter {

    protected Grammar grammar;

    public BaseWriter(Grammar grammar) {
        this.grammar = grammar;
    }

    public abstract void write(VelocityEngine engine, String templateFile);

    protected void writeTemplate(Template template, VelocityContext context, String filePath) {
        try (FileWriter writer = new FileWriter(filePath.toString())) {
            template.merge(context, writer);
        } catch (IOException e) {
            System.err.println(
                    String.format("[%s] Failed to write %s:", this.getClass().getName(), filePath.toString()));
            e.printStackTrace();
            System.exit(1);
        }
    }
}
