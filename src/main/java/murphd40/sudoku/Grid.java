package murphd40.sudoku;

import java.util.Arrays;

public class Grid {

    public Cell[][] cells = new Cell[9][9];

    Grid(Cell[][] cells) {
        this.cells = cells;
    }

    Grid transpose() {
        Cell[][] transposeCells = new Cell[9][9];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                transposeCells[i][j] = cells[j][i];
            }
        }

        return new Grid(transposeCells);
    }

    boolean isSolved() {
        return Arrays.stream(cells).flatMap(Arrays::stream).allMatch(Cell::isSolved);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Cell[] row : cells) {
            sb.append('\n');
            Arrays.stream(row).forEach(sb::append);
        }

        return sb.toString();
    }
}
