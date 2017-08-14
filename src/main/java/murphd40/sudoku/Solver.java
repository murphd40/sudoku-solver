package murphd40.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.util.Combinations;

public final class Solver {

    private Grid grid;

    private List<CellGroup> groups;

    public Solver(Grid grid) {
        this.grid = grid;
        this.groups = new ArrayList<>();
        groups.addAll(getRows(grid));
        groups.addAll(getColumns(grid));
        groups.addAll(getBlocks(grid));
    }

    public Grid solve() {

        int i = 0;

        while (!grid.isSolved()) {

            // pruning
            for (CellGroup group : groups) {
                prune(group);
            }

            if (i++ > 10000) {
                throw new RuntimeException("Failed to solve in 10000 iterations!");
            }
        }

        return grid;

    }

    private static void prune(CellGroup group) {
        for (int i = 0; i < 9 - 1; i++) {
            group.getCombinations(i).forEach(cells -> {
                Set<String> mergedValues = getMergedValues(cells);

                if (mergedValues.size() == cells.size()) {
                    Sets.difference(group, cells).forEach(cell -> cell.prune(mergedValues));
                }
            });
        }
    }

    private static List<CellGroup> getRows(Grid grid) {
        return Arrays.stream(grid.cells).map(cells -> Arrays.stream(cells).collect(Collectors.toCollection(CellGroup::new)))
            .collect(Collectors.toList());
    }

    private static List<CellGroup> getColumns(Grid grid) {
        return getRows(grid.transpose());
    }

    private static List<CellGroup> getBlocks(Grid grid) {
        Map<Pair<Integer, Integer>, CellGroup> blocks = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Pair<Integer, Integer> key = Pair.of(i / 3, j / 3);
                blocks.computeIfAbsent(key, k -> new CellGroup()).add(grid.cells[i][j]);
            }
        }

        return new ArrayList<>(blocks.values());
    }

    private static Set<String> getMergedValues(Set<Cell> cells) {
        return cells.stream().flatMap(cell -> cell.values.stream()).collect(Collectors.toSet());
    }

    private static class CellGroup extends LinkedHashSet<Cell> {

        Set<Set<Cell>> getCombinations(int k) {
            List<Cell> cells = new ArrayList<>(this);
            return StreamSupport.stream(new Combinations(size(), k).spliterator(), false)
                .map(c -> Arrays.stream(c).mapToObj(cells::get).collect(Collectors.toSet())).collect(Collectors.toSet());
        }

    }

}
