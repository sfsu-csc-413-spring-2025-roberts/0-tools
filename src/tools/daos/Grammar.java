package tools.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grammar {
    private Set<String> nonTerminals;
    private Set<String> terminals;

    private List<GrammarSymbol> keywords;
    private List<GrammarSymbol> types;
    private List<GrammarSymbol> operators;

    private Map<String, List<String>> productions;
    private SymbolicConstants symbolicConstants;

    public Grammar(SymbolicConstants symbolicConstants) {
        this.symbolicConstants = symbolicConstants;

        this.nonTerminals = new HashSet<>();
        this.terminals = new HashSet<>();

        this.keywords = new ArrayList<>();
        this.types = new ArrayList<>();
        this.operators = new ArrayList<>();

        this.productions = new HashMap<>();
    }

    public void addNonTerminal(String nonTerminal) {
        this.nonTerminals.add(nonTerminal.replace("*", ""));
    }

    public void addTerminal(String nonTerminal, String quotedTerminal) {
        String terminal = quotedTerminal.replace("'", "");

        this.terminals.add(terminal);

        if (symbolicConstants.getConstant(terminal) != null) {
            this.operators.add(new GrammarSymbol(terminal, symbolicConstants.getConstant(terminal)));
        } else if (nonTerminal.equals("TYPE")) {
            String upCased = String.format("%s%s", terminal.substring(0, 1).toUpperCase(), terminal.substring(1));

            this.types.add(new GrammarSymbol(terminal, String.format("%sType", upCased)));
            this.types.add(new GrammarSymbol(String.format("<%s>", terminal), String.format("%sLit", upCased)));
        } else {
            this.keywords.add(new GrammarSymbol(terminal, terminal));
        }
    }

    public void addProduction(String nonTerminal, String rule) {
        if (!this.productions.containsKey(nonTerminal)) {
            this.productions.put(nonTerminal, new ArrayList<>());
        }

        this.productions.get(nonTerminal).add(rule);

        String[] ruleParts = rule.split("\\s+");
        for (String rulePart : ruleParts) {
            if (rulePart.startsWith("'")) {
                this.addTerminal(nonTerminal, rulePart);
            } else {
                this.addNonTerminal(nonTerminal);
            }
        }
    }

    public List<String> getNonTerminals() {
        return nonTerminals.stream().toList();
    }

    public List<String> getTerminals() {
        return terminals.stream().toList();
    }

    private List<GrammarSymbol> intrinsics;

    public List<GrammarSymbol> getIntrinsics() {
        if (intrinsics == null) {
            intrinsics = new ArrayList<>();

            intrinsics.add(new GrammarSymbol("\0", "EOF"));
            intrinsics.add(new GrammarSymbol("BogusToken", "BogusToken"));
        }

        return intrinsics;
    }

    public List<GrammarSymbol> getKeywords() {
        return keywords;
    }

    public List<GrammarSymbol> getTypes() {
        return types;
    }

    public List<GrammarSymbol> getOperators() {
        return operators;
    }

    public List<String> getProductions() {
        return productions.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(rule -> String.format("%-15s ::= %s", entry.getKey(), rule)))
                .toList();
    }
}