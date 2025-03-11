package tools.writers.lexer;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.writers.BaseWriter;

public class TestLexerWriter extends BaseWriter {

    public TestLexerWriter(Grammar grammar) {
        super(grammar);
    }

    @Override
    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        context.put("lexerPackageName", ProjectConfiguration.lexerPackageName);
        context.put("daoPackageName", ProjectConfiguration.daosPackageName);
        context.put("lexicalExceptionName", ProjectConfiguration.exception);
        context.put("tokenKindClass", ProjectConfiguration.tokenKind);

        Template template = engine.getTemplate(templateFile);

        Path filePath = Path.of("tests", "helpers", "TestLexer.java");

        this.writeTemplate(template, context, filePath.toString());
    }

}
