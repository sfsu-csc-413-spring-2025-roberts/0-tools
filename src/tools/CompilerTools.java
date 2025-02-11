package tools;

import java.nio.file.Path;
import java.util.Arrays;

import tools.grammar.Grammar;
import tools.readers.GrammarReader;
import tools.writers.TextWriter;

public class CompilerTools {
    public static void main(String[] args) throws Exception {
        if (args.length == 0 || Arrays.asList(args).contains("-help") || Arrays.asList(args).contains("-h")) {
            System.out.println("Usage: java CompilerTools <grammar-definition-filepath>");
            System.out.println("Options:");
            System.out.printf("   %-15s %s%n", "-d --debug",
                    "Debug mode to output debugging information while reading the grammar");
            System.out.printf("   %-15s %s%n", "-h --help", "Show this help message");
            System.out.printf("   %-15s %s%n", "-v --verbose", "Output result of reading the grammar file");
            System.out.printf("   %-15s %s%n", "-1 --lexer",
                    "(Re)Generate the files for the lexer (token kind enumeration and symbol table)");
            System.out.printf("   %-15s %s%n", "-2 --parser",
                    "(Re)Generate the files for the lexer (ASTs) and basic visitors");
            System.exit(1);
        }

        boolean debug = hasCommandLineArgument(args, "--debug", "-d");
        boolean verbose = hasCommandLineArgument(args, "--verbose", "-v");

        boolean parser = hasCommandLineArgument(args, "--parser", "-2");
        boolean lexer = hasCommandLineArgument(args, "--lexer", "-1") || parser;

        Grammar grammar = new GrammarReader(Path.of(args[0]), debug).read();

        if (verbose) {
            new TextWriter(grammar).write();
        }

        if (parser) {
            System.out.println("Generating parser files...");
        }

        if (lexer) {
            System.out.println("Generating lexer files...");
        }
    }

    private static boolean hasCommandLineArgument(String[] args, String... options) {
        return args.length > 1 && Arrays.asList(args).stream().anyMatch(arg -> Arrays.asList(options).contains(arg));
    }
}
