package tools;

import java.nio.file.Path;

import tools.grammar.Grammar;
import tools.readers.GrammarReader;
import tools.writers.TextWriter;

public class CompilerTools {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java CompilerTools <grammar-definition-filepath>");
            System.exit(1);
        }

        Grammar grammar = new GrammarReader(Path.of(args[0])).read();

        TextWriter writer = new TextWriter(grammar);
        writer.write();
    }
}
