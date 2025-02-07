package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

public class MazeSolverRecursivo implements MazeSolver{

    @Override
    public List<Cell> getPath(Maze maze, boolean[][] grid, Cell start, Cell end) {
        
        List<Cell> path = new ArrayList<>();
        Set<Cell> visitadas = new HashSet<>();
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
        
    private boolean findPath(Maze maze, boolean[][] grid, int row, int col, Cell end, Cell start, List<Cell> path, Set<Cell> visitados) {
        Cell cell = new Cell(row, col);
        
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || !grid[row][col]){
            return false;
        }

        if (visitados.contains(cell)) {
            return false;
        } 

        visitados.add(cell);
        maze.updateMaze(cell, start, end);
        
        if (row == end.row && col == end.col){
            path.add(cell);
            return true;
        }

        if (findPath(maze, grid, row + 1, col, end, start, path, visitados)){
            path.add(cell);
            return true;
        }

        if (findPath(maze, grid, row, col + 1, end, start, path, visitados)) {
            path.add(cell);
            return true;
        }

        if (findPath(maze, grid, row - 1, col, end, start, path, visitados)){
            path.add(cell);
            return true;
        }

        if (findPath(maze, grid, row, col - 1, end, start, path, visitados)) {
            path.add(cell);
            return true;
        }

        //visitados.remove(cell);
        path.remove(cell);

        return false;
    }
    
}
