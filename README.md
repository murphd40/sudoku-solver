# sudoku-solver

The Solver works using only this rule:

```math
Given a cell group G. If there exists a set of cells S subsetOf G such that |S| == |V_S|,
where V_S is the merged set of possible values for the cells in S, then none of the cells
in G\S can contain any of the values in V_S
```