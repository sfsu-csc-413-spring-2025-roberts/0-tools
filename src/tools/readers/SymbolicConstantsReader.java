package tools.readers;

import java.io.IOException;
import java.nio.file.Path;

import tools.daos.SymbolicConstants;

public class SymbolicConstantsReader extends ToolFileReader {
    private SymbolicConstants constants;

    public SymbolicConstantsReader(Path path) throws IOException {
        super(path);

        this.constants = new SymbolicConstants();
    }

    @Override
    public void read() {
        while (this.hasNext()) {
            String[] line = this.next().split("\\s+");

            this.constants.addConstant(line[0].trim(), line[1].trim());
        }
    }

    public SymbolicConstants getConstants() {
        return this.constants;
    }

    public static void main(String[] args) {
        try {
            SymbolicConstantsReader reader = new SymbolicConstantsReader(
                    Path.of("src/config/lexeme-constant-map.txt"));
            reader.read();

            System.out.println(reader.constants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
