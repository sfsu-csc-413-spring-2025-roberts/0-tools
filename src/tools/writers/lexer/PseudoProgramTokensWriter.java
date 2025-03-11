package tools.writers.lexer;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.writers.BaseWriter;

public class PseudoProgramTokensWriter extends BaseWriter {

    public PseudoProgramTokensWriter(Grammar grammar) {
        super(grammar);
    }

    @Override
    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        context.put("lexerPackageName", ProjectConfiguration.lexerPackageName);
        context.put("daoPackageName", ProjectConfiguration.daosPackageName);

        context.put("tokenKindClass", ProjectConfiguration.tokenKind);
        context.put("tableName", ProjectConfiguration.tableName);

        context.put("literals", this.grammar.getTypes());
        context.put("keywords", this.grammar.getKeywords());
        context.put("operators", this.grammar.getOperators());
        context.put("separators", this.grammar.getSeparators());

        Template template = engine.getTemplate(templateFile);

        Path filePath = Path.of("tests", "helpers", "PseudoProgramTokens.java");

        this.writeTemplate(template, context, filePath.toString());
    }

}
