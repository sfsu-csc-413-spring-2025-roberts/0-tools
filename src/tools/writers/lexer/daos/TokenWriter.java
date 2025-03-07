package tools.writers.lexer.daos;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.writers.BaseWriter;

public class TokenWriter extends BaseWriter {

    public TokenWriter(Grammar grammar) {
        super(grammar);
    }

    @Override
    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        context.put("packageName", ProjectConfiguration.lexerPackageName);
        context.put("daoPackageName", ProjectConfiguration.daosPackageName);

        context.put("tokenKind", ProjectConfiguration.tokenKind);

        Template template = engine.getTemplate(templateFile);

        Path filePath = Path.of(ProjectConfiguration.daoPackagePath.toString(), "Token.java");

        if (filePath.toFile().exists()) {
            System.out
                    .println(String.format("[%s] %s exists; skipping generation to avoid overwriting student changes.",
                            this.getClass().getName(), filePath.toString()));
            return;
        }

        this.writeTemplate(template, context, filePath.toString());
    }

}
