package tools;

import java.nio.file.Path;
import java.util.Arrays;

import tools.grammar.Grammar;
import tools.readers.GrammarReader;
import tools.writers.AstBaseClassWriter;
import tools.writers.AstNodeWithSymbolWriter;
import tools.writers.AstNodeWriter;
import tools.writers.PrintVisitorWriter;
import tools.writers.SummaryWriter;
import tools.writers.SymbolTableWriter;
import tools.writers.TextWriter;
import tools.writers.TokenKindWriter;
import tools.writers.VisitorBaseClassWriter;
import tools.writers.VisitorTemplateWriter;

public class CompilerTools {
    public static void main(String[] args) throws Exception {
        if (args.length == 0 || Arrays.asList(args).contains("-help") || Arrays.asList(args).contains("-h")) {
            System.out.println("Usage: java CompilerTools <grammar-definition-filepath>");
            System.out.println("Options:");
            System.out.printf("   %-15s %s%n", "-d --debug",
                    "Debug mode to output debugging information while reading the grammar");
            System.out.printf("   %-15s %s%n", "-h --help", "Show this help message");
            System.out.printf("   %-15s %s%n", "-v --verbose", "Output result of reading the grammar file");
            System.out.printf("   %-15s %s%n", "-s --summary", "Output concise result of reading the grammar file");
            System.out.printf("   %-15s %s%n", "-1 --lexer",
                    "(Re)Generate the files for the lexer (token kind enumeration and symbol table)");
            System.out.printf("   %-15s %s%n", "-2 --parser",
                    "(Re)Generate the files for the parser (ASTs) and basic visitors");
            System.out.printf("   %-15s %s%n", "-n CLASS_NAME --new-visitor CLASS_NAME",
                    "Generate a new visitor class");
            System.exit(1);
        }

        boolean debug = hasCommandLineArgument(args, "--debug", "-d");
        boolean verbose = hasCommandLineArgument(args, "--verbose", "-v");
        boolean summary = hasCommandLineArgument(args, "--summary", "-s");

        boolean parser = hasCommandLineArgument(args, "--parser", "-2");
        boolean lexer = hasCommandLineArgument(args, "--lexer", "-1") || parser;
        boolean newVisitor = hasCommandLineArgument(args, "--new-visitor", "-n");

        Grammar grammar = new GrammarReader(Path.of(args[0]), debug).read();

        if (summary) {
            new SummaryWriter(grammar).write();
        }

        if (verbose) {
            new TextWriter(grammar).write();
        }

        if (newVisitor) {
            String className = getCommandLineArgumentValue(args, "--new-visitor", "-n");

            new VisitorTemplateWriter(grammar, className).write();
            return;
        }

        if (lexer) {
            new TokenKindWriter(grammar).write();
            new SymbolTableWriter(grammar).write();
        }

        if (parser) {
            new AstBaseClassWriter(grammar).write();

            AstNodeWriter.prepareNodeDirectory();
            new AstNodeWriter(grammar).write();
            new AstNodeWithSymbolWriter(grammar).write();

            VisitorBaseClassWriter.prepareVisitorDirectory();
            new VisitorBaseClassWriter(grammar).write();
            new PrintVisitorWriter(grammar).write();
        }
    }

    private static boolean hasCommandLineArgument(String[] args, String... options) {
        return args.length > 1 && Arrays.asList(args).stream().anyMatch(arg -> Arrays.asList(options).contains(arg));
    }

    private static String getCommandLineArgumentValue(String[] args, String... options) {
        for (String option : options) {
            int index = Arrays.asList(args).indexOf(option);

            if (index == -1 || index + 1 >= args.length) {
                return null;
            }

            return args[index + 1];
        }

        return null;
    }
}
