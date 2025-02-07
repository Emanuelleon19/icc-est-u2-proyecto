package controllers;

import java.util.*;

import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

public class MazeSolverDP implements MazeSolver {
    private Map<Cell, Boolean> memoria = new HashMap<>();

    @Override
    public List<Cell> getPath(Maze maze, boolean[][] grid, Cell start, Cell end) {
        List<Cell> path = new ArrayList<>();
        Set<Cell> visitadas = new HashSet();
        if (grid == null || grid.length == 0){
            return path;
        }

        //if (findPath(grid, start.row, start.col, end, path, visitadas)) {
            //return path;
        //}
        findPath(maze, grid, start.row, start.col, end, start, path, visitadas);
        List<Cell> pathRecorrido = new ArrayList<>(visitadas);
        
        return path.isEmpty()? pathRecorrido : path;
    }

    private boolean findPath(Maze maze, boolean[][] grid, int row, int col, Cell end, Cell start, List<Cell> path,  Set<Cell> visitadas) {
        Cell cell = new Cell(row, col);

        if(row < 0 || col< 0 || row >= grid.length || col >= grid[0].length || !grid[row][col] || memoria.containsKey(cell)){
            return false;
        }

        // validamos la llegada al fin
        if(row == end.row && col == end.col){
            path.add(cell);
            return true;
        }

        if(visitadas.contains(cell)){
             return false;
        }
        visitadas.add(cell);

        if(findPath(maze, grid, row + 1, col, end, start, path, visitadas) || findPath(maze, grid, row, col + 1, end, start, path, visitadas)){
            path.add(cell);
            memoria.put(cell, true);
            return true;
        }

        if(findPath(maze, grid, row - 1, col, end, start, path, visitadas) || findPath(maze, grid, row, col - 1, end, start, path, visitadas)){
            path.add(cell);
            memoria.put(cell, true);
            return true;
        }

        memoria.put(cell, false);
        return false;
    }

}
