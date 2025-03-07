package tools.writers.lexer.daos;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.writers.BaseWriterWithClassName;

public class TokenKindWriter extends BaseWriterWithClassName {

    public TokenKindWriter(Grammar grammar, String className) {
        super(grammar, className);
    }

    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        context.put("packageName", ProjectConfiguration.lexerPackageName);
        context.put("daoPackageName", ProjectConfiguration.daosPackageName);

        context.put("tokenKind", ProjectConfiguration.tokenKind);

        context.put("intrinsics", this.grammar.getInternals());
        context.put("literals", this.grammar.getTypes());
        context.put("keywords", this.grammar.getKeywords());
        context.put("operators", this.grammar.getOperators());
        context.put("separators", this.grammar.getSeparators());

        Template template = engine.getTemplate(templateFile);

        Path filePath = Path.of(ProjectConfiguration.daoPackagePath.toString(),
                String.format("%s.java", ProjectConfiguration.tokenKind));

        this.writeTemplate(template, context, filePath.toString());
    }
}
