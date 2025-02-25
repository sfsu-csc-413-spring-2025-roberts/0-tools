package tools.writers;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tools.grammar.Grammar;

public class VisitorTemplateWriter extends AstNodeWriter {

    private File file;
    private String className;

    public VisitorTemplateWriter(Grammar grammar, String className) {
        super(grammar);

        this.className = className;
        this.file = new File(String.format("src/visitor/%s.java", className));
    }

    @Override
    public void write() throws Exception {
        Files.createDirectories(Path.of(this.file.getParent()));
        if (this.file.exists()) {
            this.file.delete();
        }

        try (FileWriter writer = new FileWriter(this.file)) {
            writer.write(this.formattedLine("package visitor;", 0, 2));

            writer.write(this.formattedLine("import ast.*;", 0, 2));

            writer.write(
                    this.formattedLine(String.format("public class %s extends ASTVisitor {", this.className), 0, 2));

            List<String> nodes = Stream.concat(this.grammar.getAstsWithoutSymbols().stream(),
                    this.grammar.getAstsWithoutSymbols().stream()).map(super::getClassNameFromGrammar)
                    .collect(Collectors.toList());

            for (String node : nodes) {
                writer.write(this.formattedLine(String.format("public AST visit%s(AST node) {", node), 1, 1));
                writer.write(this.formattedLine("return null;", 2, 1));
                writer.write(this.formattedLine("}", 1, 2));
            }

            writer.write(this.formattedLine("}", 0, 2));
        }
    }

}
