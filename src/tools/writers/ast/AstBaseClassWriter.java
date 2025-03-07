package tools.writers.ast;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.writers.BaseWriterWithClassName;

public class AstBaseClassWriter extends BaseWriterWithClassName {

    public AstBaseClassWriter(Grammar grammar, String className) {
        super(grammar, className);
    }

    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        context.put("astPackageName", ProjectConfiguration.astPackageName);
        context.put("astClassName", ProjectConfiguration.astClassName);
        context.put("visitorPackageName", ProjectConfiguration.visitorPackageName);
        context.put("visitorClassName", ProjectConfiguration.visitorClassName);

        Template template = engine.getTemplate(templateFile);

        Path filePath = Path.of(ProjectConfiguration.astPackagePath.toString(),
                String.format("%s.java", ProjectConfiguration.astClassName));

        this.writeTemplate(template, context, filePath.toString());
    }
}
