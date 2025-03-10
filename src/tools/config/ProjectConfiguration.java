package tools.config;

import java.nio.file.Path;

public class ProjectConfiguration {
    /**
     * Lexer
     */
    public static final String lexerPackageName = "lexer";
    public static final String daosPackageName = "daos";
    public static final String exception = "LexicalException";
    public static final String tokenKind = "TokenKind";
    public static final String tableName = "SymbolTable";
    public static final String bogusName = "BogusToken";
    public static final String nextMethod = "nextToken";

    public static final Path lexerPackagePath = Path.of("src", lexerPackageName);
    public static final Path daoPackagePath = Path.of("src", lexerPackageName, daosPackageName);

    /**
     * Asts
     */
    public static final String astClassName = "Ast";
    public static final String interfaceWithTokenName = "TokenTree";
    public static final String astPackageName = "ast";
    public static final String treePackageName = "nodes";

    public static final Path astPackagePath = Path.of("src", astPackageName);
    public static final Path treePackagePath = Path.of("src", astPackageName, treePackageName);

    /**
     * Visitor
     */
    public static final String visitorClassName = "ASTVisitor";
    public static final String visitorPackageName = "visitor";
    public static final String printVisitorClassName = "PrintVisitor";

    public static final Path visitorPackagePath = Path.of("src", visitorPackageName);
}
