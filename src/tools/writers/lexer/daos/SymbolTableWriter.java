package tools.writers.lexer.daos;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.writers.BaseWriter;

public class SymbolTableWriter extends BaseWriter {

    public SymbolTableWriter(Grammar grammar) {
        super(grammar);
    }

    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        context.put("packageName", ProjectConfiguration.lexerPackageName);
        context.put("daoPackageName", ProjectConfiguration.daosPackageName);

        context.put("tableName", ProjectConfiguration.tableName);

        context.put("symbolicConstants", this.grammar.getAllSymbolicConstants());
        context.put("tokenKind", ProjectConfiguration.tokenKind);
        context.put("bogusName", ProjectConfiguration.bogusName);

        Template template = engine.getTemplate(templateFile);

        Path filePath = Path.of(ProjectConfiguration.daoPackagePath.toString(),
                String.format("%s.java", ProjectConfiguration.tableName));

        this.writeTemplate(template, context, filePath.toString());
    }
}
