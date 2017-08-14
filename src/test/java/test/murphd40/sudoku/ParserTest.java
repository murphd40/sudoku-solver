package test.murphd40.sudoku;

import murphd40.sudoku.Grid;
import murphd40.sudoku.Parser;
import org.junit.Test;

public class ParserTest {

    private static final String content = "1,2,3,4,5,6,7,8,9;\n" +
        "1,2,3,4,5,6,7,8,9;\n" +
        "1,2,3,4,5,6,7,8,9;\n" +
        "1,2,3,4,5,6,7,8,9;\n" +
        "1,2,3,4,5,6,7,8,9;\n" +
        "1,2,3,4,5,6,7,8,9;\n" +
        "1,2,3,4,5,6,7,8,9;\n" +
        "1,2,3,4,5,6,7,8,9;\n" +
        "1,2,3,4,5,6,7,8,9;";

    @Test
    public void parserTest() {
        Grid grid = Parser.parse(content);

        System.out.println(grid);
    }

}
