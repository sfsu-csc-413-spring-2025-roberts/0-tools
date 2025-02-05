package tools.readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

public abstract class ToolFileReader implements Iterator<String> {
    private int index;
    private List<String> lines;

    public ToolFileReader(Path path) throws IOException {
        this.index = 0;

        this.lines = Files.readAllLines(path).stream()
                .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                .toList();
    }

    @Override
    public boolean hasNext() {
        return this.index < this.lines.size();
    }

    @Override
    public String next() {
        return this.lines.get(this.index++);
    }

    public abstract void read();
}
