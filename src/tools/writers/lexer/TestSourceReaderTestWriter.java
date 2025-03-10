package tools.writers.lexer;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.grammar.Grammar;
import tools.writers.BaseWriter;

public class TestSourceReaderTestWriter extends BaseWriter {

    public TestSourceReaderTestWriter(Grammar grammar) {
        super(grammar);
    }

    @Override
    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        Template template = engine.getTemplate(templateFile);

        Path filePath = Path.of("tests", "helpers", "TestSourceReaderTest.java");

        this.writeTemplate(template, context, filePath.toString());
    }

}
