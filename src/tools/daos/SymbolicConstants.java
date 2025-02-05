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
}
