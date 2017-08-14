package murphd40.sudoku;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.Sets;

public class Cell {

    private Set<String> values;

    private Cell(Set<String> values) {
        this.values = values;
    }

    public static Cell of(int size) {
        return new Cell(Sets.newHashSet("1", "2", "3", "4", "5", "6", "7", "8", "9"));
    }

    public static Cell solved(String value) {
        return new Cell(Collections.singleton(value));
    }

    @Override
    public String toString() {
        return values.toString();
    }
}
