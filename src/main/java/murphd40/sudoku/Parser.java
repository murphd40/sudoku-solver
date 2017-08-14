package murphd40.sudoku;

import org.apache.commons.lang3.StringUtils;

public final class Parser {

    private Parser() {}

    public static Grid parse(String content) {

        String cleanContent = StringUtils.deleteWhitespace(content);

        Grid grid = new Grid();

        String[] rows = cleanContent.split(";");

        for (int i = 0; i < 9; i++) {
            String[] entries = rows[i].split(",");
            for (int j = 0; j < 9; j++) {
                String entry = entries[j];
                grid.cells[i][j] = StringUtils.isEmpty(entry) ? Cell.of(9) : Cell.solved(entry);
            }
        }

        return grid;
    }

}
