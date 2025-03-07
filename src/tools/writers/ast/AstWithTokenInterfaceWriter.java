package tools.writers.ast;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.writers.BaseWriterWithClassName;

public class AstWithTokenInterfaceWriter extends BaseWriterWithClassName {

    public AstWithTokenInterfaceWriter(Grammar grammar, String className) {
        super(grammar, className);
    }

    @Override
    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        context.put("astPackageName", ProjectConfiguration.astPackageName);

        context.put("lexerPackageName", ProjectConfiguration.lexerPackageName);
        context.put("daoPackageName", ProjectConfiguration.daosPackageName);

        context.put("interfaceName", ProjectConfiguration.interfaceWithTokenName);

        Template template = engine.getTemplate(templateFile);

        Path filePath = Path.of(ProjectConfiguration.astPackagePath.toString(),
                String.format("%s.java", ProjectConfiguration.interfaceWithTokenName));

        this.writeTemplate(template, context, filePath.toString());
    }
}
