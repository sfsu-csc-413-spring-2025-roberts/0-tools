package tools;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.apache.velocity.app.VelocityEngine;

import tools.config.ProjectConfiguration;
import tools.grammar.Grammar;
import tools.readers.GrammarReader;
import tools.writers.ast.*;
import tools.writers.lexer.*;
import tools.writers.lexer.daos.*;
import tools.writers.parser.PseudoProgramAstWriter;
import tools.writers.parser.PseudoProgramWriter;
import tools.writers.visitor.*;

public class CompilerTools {
    public static void main(String[] args) throws Exception {
        if (args.length == 0 || Arrays.asList(args).contains("-help") || Arrays.asList(args).contains("-h")) {
            System.out.println("Usage: java CompilerTools <grammar-definition-filepath>");
            System.out.println("Options:");
            System.out.printf("   %-15s %s%n", "-d --debug",
                    "Debug mode to output debugging information while reading the grammar");
            System.out.printf("   %-15s %s%n", "-h --help", "Show this help message");
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
        boolean summary = hasCommandLineArgument(args, "--summary", "-s");

        boolean parser = hasCommandLineArgument(args, "--parser", "-2");
        boolean lexer = hasCommandLineArgument(args, "--lexer", "-1") || parser;
        boolean newVisitor = hasCommandLineArgument(args, "--new-visitor", "-n");

        Grammar grammar = new GrammarReader(Path.of(args[0]), debug).read();

        if (summary) {
            System.out.println(grammar);
        }

        VelocityEngine engine = new VelocityEngine();
        engine.init();

        if (newVisitor) {
            String className = getCommandLineArgumentValue(args, "--new-visitor", "-n");
            System.out.println(String.format("Creating class %s", className));

            new CustomVisitorWriter(grammar, className).write(engine,
                    "src/tools/templates/visitor/custom-visitor.java.vm");
            return;
        }

        createDirectory(Path.of("tests", "helpers"), true);

        if (lexer) {
            System.out.println("Creating lexer daos...");
            createDirectory(ProjectConfiguration.daoPackagePath, true,
                    Path.of(ProjectConfiguration.daoPackagePath.toString(), "Token.java"));
            new TokenKindWriter(grammar, ProjectConfiguration.tokenKind).write(engine,
                    "src/tools/templates/lexer/daos/token-kind.java.vm");
            new SymbolWriter(grammar).write(engine, "src/tools/templates/lexer/daos/symbol.java.vm");
            new SymbolTableWriter(grammar).write(engine, "src/tools/templates/lexer/daos/symbol-table.java.vm");
            new TokenWriter(grammar).write(engine, "src/tools/templates/lexer/daos/token.java.vm");

            System.out.println("Creating lexer testing classes...");
            new TestSourceReaderWriter(grammar).write(engine, "src/tools/templates/lexer/test-source-reader.java.vm");
            new TestSourceReaderTestWriter(grammar).write(engine,
                    "src/tools/templates/lexer/test-source-reader-test.java.vm");
        }

        if (parser) {
            System.out.println("Creating AST base classes...");
            createDirectory(ProjectConfiguration.astPackagePath, true);
            new AstBaseClassWriter(grammar, ProjectConfiguration.astClassName).write(engine,
                    "src/tools/templates/ast/base-class.java.vm");
            new AstWithTokenInterfaceWriter(grammar, ProjectConfiguration.interfaceWithTokenName).write(engine,
                    "src/tools/templates/ast/interface-with-token.java.vm");

            System.out.println("Creating AST classes...");
            createDirectory(ProjectConfiguration.treePackagePath, true);
            new NodeWriter(grammar).write(engine, "src/tools/templates/ast/class-*-token.java.vm");

            System.out.println("Creating visitor classes...");
            createDirectory(ProjectConfiguration.visitorPackagePath, true);
            new VisitorWriter(grammar, ProjectConfiguration.visitorClassName).write(engine,
                    "src/tools/templates/visitor/visitor.java.vm");
            new PrintVisitorWriter(grammar, ProjectConfiguration.printVisitorClassName).write(engine,
                    "src/tools/templates/visitor/output-visitor.java.vm");

            System.out.println("Creating ast testing classes...");
            new TestVisitorWriter(grammar).write(engine, "src/tools/templates/visitor/test-visitor.java.vm");
            new TestLexerWriter(grammar).write(engine, "src/tools/templates/lexer/test-lexer.java.vm");
            new PseudoProgramTokensWriter(grammar).write(engine,
                    "src/tools/templates/lexer/pseudo-program-tokens.java.vm");
            new PseudoProgramAstWriter(grammar).write(engine, "src/tools/templates/ast/pseudo-program-asts.java.vm");
            new PseudoProgramWriter(grammar).write(engine, "src/tools/templates/parser/pseudo-program.java.vm");
        }
    }

    private static void createDirectory(Path path, boolean deleteAll, Path... excluded) throws Exception {
        if (path.toFile().exists() && !path.toFile().isDirectory()) {
            throw new Exception(
                    String.format("The path [%s] exists, and is not a directory. Could not create.", path.toString()));
        }

        List<Path> excludedFiles = Arrays.asList(excluded);

        if (path.toFile().exists() && deleteAll) {
            for (File file : path.toFile().listFiles()) {
                if (!file.isDirectory() && !excludedFiles.contains(file.toPath())) {
                    file.delete();
                }
            }
        }

        Files.createDirectories(path);
    }

    private static boolean hasCommandLineArgument(String[] args, String... options) {
        return args.length > 1 && Arrays.asList(args).stream().anyMatch(arg -> Arrays.asList(options).contains(arg));
    }

    private static String getCommandLineArgumentValue(String[] args, String... options) {
        for (String option : options) {
            int index = Arrays.asList(args).indexOf(option);

            if (index == -1 || index + 1 >= args.length) {
                continue;
            }

            return args[index + 1];
        }

        return null;
    }
}
