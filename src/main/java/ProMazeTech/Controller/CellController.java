package ProMazeTech.Controller;
import ProMazeTech.Model.Cell;
import ProMazeTech.Model.Maze;

import java.util.ArrayList;
import java.util.Random;

public class CellController {
    Random random = new Random();
    public Cell randomNextCell(Maze maze, int x, int y) {
        ArrayList<Cell> nextCellList = new ArrayList<>();
        if (x - 1 >= 0 && !maze.getMatrix()[x - 1][y].isVisited()) {
            nextCellList.add(maze.getMatrix()[x - 1][y]);
        }
        if (y - 1 >= 0 && !maze.getMatrix()[x][y - 1].isVisited()) {
            nextCellList.add(maze.getMatrix()[x][y - 1]);
        }
        if (x + 1 < maze.getSize() && !maze.getMatrix()[x + 1][y].isVisited()) {
            nextCellList.add(maze.getMatrix()[x + 1][y]);
        }
        if (y + 1 < maze.getSize() && !maze.getMatrix()[x][y + 1].isVisited()) {
            nextCellList.add(maze.getMatrix()[x][y + 1]);
        }
        if (nextCellList.isEmpty()) {
            return null;
        }
        return nextCellList.get(random.nextInt(nextCellList.size()));
    }
}
