package tools.writers.parser;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.writers.BaseWriter;

public class PseudoProgramAstWriter extends BaseWriter {

    public PseudoProgramAstWriter(Grammar grammar) {
        super(grammar);
    }

    @Override
    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        context.put("astPackageName", ProjectConfiguration.astPackageName);
        context.put("treePackageName", ProjectConfiguration.treePackageName);

        context.put("astClassName", ProjectConfiguration.astClassName);

        Template template = engine.getTemplate(templateFile);

        Path filePath = Path.of("tests", "helpers", "PseudoProgramAsts.java");

        this.writeTemplate(template, context, filePath.toString());
    }

}
