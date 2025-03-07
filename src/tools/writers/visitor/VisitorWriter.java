package tools.writers.visitor;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.writers.BaseWriterWithClassName;

public class VisitorWriter extends BaseWriterWithClassName {

    public VisitorWriter(Grammar grammar, String className) {
        super(grammar, className);
    }

    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        context.put("visitorPackageName", ProjectConfiguration.visitorPackageName);
        context.put("astPackageName", ProjectConfiguration.astPackageName);
        context.put("astClassName", ProjectConfiguration.astClassName);
        context.put("visitorClassName", ProjectConfiguration.visitorClassName);
        context.put("astClassNames", this.grammar.getAllAsts());

        Template template = engine.getTemplate(templateFile);

        Path filePath = Path.of(ProjectConfiguration.visitorPackagePath.toString(),
                String.format("%s.java", ProjectConfiguration.visitorClassName));

        this.writeTemplate(template, context, filePath.toString());
    }
}
