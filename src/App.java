import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import controllers.MazeSolverBFS;
import controllers.MazeSolverDP;
import controllers.MazeSolverRecursivo;
import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

public class App {
    public static void main(String[] args) throws Exception {
        boolean[][] laberinto = {
            {true, true, true, true},
            {false, true, true, true},
            {true, true, false, false},
            {true, true, true, true}
        };

        Maze maze = new Maze(laberinto);    
        System.out.println("Laberinto");
        maze.printMaze();

        Cell start = new Cell(0, 3);
        Cell end = new Cell(3, 3);

        List<MazeSolver> soluciones = Arrays.asList(
            new MazeSolverRecursivo());
            //new MazeSolverDP();
            //new MazeSolverBFS());

        int option = 1;
        MazeSolver solver = soluciones.get(1 - 1);
        List<Cell> path = solver.getPath(maze, laberinto, start, end);
        System.out.println("\nCamino encontrado");
        System.out.println(path);
        System.out.println();
        System.out.println("Laberinto con el camino recorrido: ");
        maze.printMazeWithPath(path);

        
    }
    
}

