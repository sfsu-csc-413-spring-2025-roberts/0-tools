package tools;

import java.nio.file.Path;

import tools.readers.GrammarReader;

public class CompilerTools {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java CompilerTools <constants-file> <grammar-file>");
            System.exit(1);
        }

        GrammarReader reader = new GrammarReader(Path.of(args[0]), Path.of(args[1]));
    }
}
