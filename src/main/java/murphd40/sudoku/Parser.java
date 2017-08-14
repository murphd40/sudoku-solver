package murphd40.sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public final class Parser {

    private Parser() {}

    public static Grid parse(File file) throws IOException {
        String content = IOUtils.toString(file.toURI(), StandardCharsets.UTF_8);
        return parse(content);
    }

    public static Grid parse(String content) {

        String cleanContent = StringUtils.deleteWhitespace(content);

        Cell[][] cells = new Cell[9][9];

        String[] rows = cleanContent.split(";");

        for (int i = 0; i < 9; i++) {
            String[] entries = rows[i].split(",", -1);
            for (int j = 0; j < 9; j++) {
                String entry = entries[j];
                cells[i][j] = StringUtils.isEmpty(entry) ? Cell.of(9) : Cell.solved(entry);
            }
        }

        return new Grid(cells);
    }

}
