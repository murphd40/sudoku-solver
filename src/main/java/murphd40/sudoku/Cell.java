package murphd40.sudoku;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.collect.Sets;

import static java.lang.Character.MAX_RADIX;

public class Cell {

    Set<String> values;

    private Cell(Set<String> values) {
        this.values = values;
    }

    static Cell of(int size) {
        return new Cell(IntStream.range(1, size + 1).mapToObj(i -> Integer.toString(i, MAX_RADIX)).collect(Collectors.toSet()));
    }

    static Cell solved(String value) {
        return new Cell(Sets.newHashSet(value));
    }

    boolean isSolved() {
        return values.size() == 1;
    }

    void prune(Set<String> toRemove) {
        values.removeAll(toRemove);
        checkState();
    }

    private void checkState() {
        if (values.size() < 1) {
            throw new IllegalStateException("Cell has no possible values!");
        }
    }

    @Override
    public String toString() {
        return values.toString();
    }
}
