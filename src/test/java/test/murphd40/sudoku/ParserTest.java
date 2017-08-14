package test.murphd40.sudoku;

import java.io.File;
import java.io.IOException;

import murphd40.sudoku.Grid;
import murphd40.sudoku.Parser;
import murphd40.sudoku.Solver;
import org.junit.Test;

public class ParserTest {

    @Test
    public void parseFile() throws IOException {
        File file = new File("src/test/resources/test-grid.txt");
        Grid grid = Parser.parse(file);

        System.out.println(grid);
    }

    @Test
    public void solverTest() throws IOException {
        File file = new File("src/test/resources/test-grid132.txt");
        Grid grid = Parser.parse(file);

        Solver solver = new Solver(grid);

        Grid result = solver.solve();

        System.out.println(result);
    }

}
