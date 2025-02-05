package tools;

import java.io.IOException;
import java.nio.file.Path;

import tools.daos.Grammar;
import tools.daos.GrammarSymbol;
import tools.readers.GrammarReader;

public class CompilerTools {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java CompilerTools <constants-file> <grammar-file>");
            System.exit(1);
        }

        Grammar grammar = new GrammarReader(Path.of(args[1]), Path.of(args[0])).read();

        System.out.println("\n=== Productions ===");
        System.out.println(String.join(System.lineSeparator(), grammar.getProductions()));
        System.out.println();

        System.out.println("=== Non-Terminals ===");
        System.out.println(String.join(", ", grammar.getNonTerminals()));
        System.out.println();

        System.out.println("=== Terminals ===");
        System.out.println(String.join(", ", grammar.getTerminals()));
        System.out.println();

        System.out.println("=== Keywords ===");
        for (GrammarSymbol keyword : grammar.getKeywords()) {
            System.out.println(keyword);
        }
        System.out.println();

        System.out.println("=== Types ===");
        for (GrammarSymbol type : grammar.getTypes()) {
            System.out.println(type);
        }
        System.out.println();

        System.out.println("=== Operators ===");
        for (GrammarSymbol operator : grammar.getOperators()) {
            System.out.println(operator);
        }
    }
}
