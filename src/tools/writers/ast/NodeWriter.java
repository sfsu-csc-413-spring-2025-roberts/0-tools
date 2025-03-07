package tools.writers.ast;

import java.nio.file.Path;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.writers.BaseWriter;

public class NodeWriter extends BaseWriter {

    public NodeWriter(Grammar grammar) {
        super(grammar);
    }

    @Override
    public void write(VelocityEngine engine, String templateFile) {
        VelocityContext context = new VelocityContext();

        context.put("astPackageName", ProjectConfiguration.astPackageName);
        context.put("treePackageName", ProjectConfiguration.treePackageName);
        context.put("astClassName", ProjectConfiguration.astClassName);
        context.put("visitorPackageName", ProjectConfiguration.visitorPackageName);
        context.put("visitorClassName", ProjectConfiguration.visitorClassName);
        context.put("lexerPackageName", ProjectConfiguration.lexerPackageName);
        context.put("daoPackageName", ProjectConfiguration.daosPackageName);
        context.put("interfaceWithTokenName", ProjectConfiguration.interfaceWithTokenName);

        Template basicTemplate = engine.getTemplate(templateFile.replace("*", "without"));
        Template withTokenTemplate = engine.getTemplate(templateFile.replace("*", "with"));

        for (String ast : this.grammar.getAsts()) {
            context.put("className", ast);

            this.writeTemplate(basicTemplate, context, Path.of(
                    ProjectConfiguration.treePackagePath.toString(),
                    String.format("%s.java", ast)).toString());
        }

        for (String ast : this.grammar.getAstsWithTokens()) {
            context.put("className", ast);

            this.writeTemplate(withTokenTemplate, context, Path.of(
                    ProjectConfiguration.treePackagePath.toString(),
                    String.format("%s.java", ast)).toString());
        }

    }

}
