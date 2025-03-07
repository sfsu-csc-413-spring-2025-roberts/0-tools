package tools.writers.lexer.daos;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.writers.BaseWriter;

public class SymbolWriter extends BaseWriter {

    public SymbolWriter(Grammar grammar) {
        super(grammar);
    }

    @Override
    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        context.put("packageName", ProjectConfiguration.lexerPackageName);
        context.put("daoPackageName", ProjectConfiguration.daosPackageName);

        context.put("tokenKind", ProjectConfiguration.tokenKind);

        Template template = engine.getTemplate(templateFile);

        Path filePath = Path.of(ProjectConfiguration.daoPackagePath.toString(), "Symbol.java");

        this.writeTemplate(template, context, filePath.toString());
    }

}
