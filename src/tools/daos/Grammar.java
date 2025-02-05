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

    public Grammar() {
        this.nonTerminals = new HashSet<>();
        this.terminals = new HashSet<>();

        this.keywords = new ArrayList<>();
        this.types = new ArrayList<>();
        this.operators = new ArrayList<>();

        this.productions = new HashMap<>();
    }

    public List<String> getNonTerminals() {
        return nonTerminals.stream().toList();
    }

    public List<String> getTerminals() {
        return terminals.stream().toList();
    }

    public List<GrammarSymbol> getIntrinsics() {
        return null;
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

    public List<String> getProductions(String nonTerminal) {
        return productions.get(nonTerminal);
    }
}