package tools.writers.visitor;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.writers.BaseWriter;

public class TestVisitorWriter extends BaseWriter {

    public TestVisitorWriter(Grammar grammar) {
        super(grammar);
    }

    @Override
    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        context.put("visitorPackageName", ProjectConfiguration.visitorPackageName);
        context.put("visitorClassName", ProjectConfiguration.visitorClassName);

        context.put("astPackageName", ProjectConfiguration.astPackageName);
        context.put("astClassName", ProjectConfiguration.astClassName);
        context.put("interfaceWithTokenName", ProjectConfiguration.interfaceWithTokenName);

        context.put("astsWithoutTokens", this.grammar.getAsts());
        context.put("astsWithTokens", this.grammar.getAstsWithTokens());

        Template template = engine.getTemplate(templateFile);

        Path filePath = Path.of("src", "tests", "helpers", "TestVisitor.java");

        this.writeTemplate(template, context, filePath.toString());
    }

}
