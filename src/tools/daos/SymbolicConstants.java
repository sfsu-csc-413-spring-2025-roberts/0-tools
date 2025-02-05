package tools.daos;

import java.util.HashMap;
import java.util.Map;

public class SymbolicConstants {
    private Map<String, String> constants;

    public SymbolicConstants() {
        this.constants = new HashMap<>();
    }

    public String getConstant(String lexeme) {
        return this.constants.get(lexeme);
    }

    public void addConstant(String lexeme, String value) {
        this.constants.put(lexeme, value);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), this.constants.entrySet().stream()
                .map(entry -> String.format("%-5s %s", entry.getKey(), entry.getValue())).toList());
    }
}
