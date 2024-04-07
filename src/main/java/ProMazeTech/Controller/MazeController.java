package ProMazeTech.Controller;
import ProMazeTech.Model.Cell;
import ProMazeTech.Model.Maze;

import java.util.Stack;

public class MazeController {
    CellController cellController = new CellController();

    public void updateWall(Maze maze, int x, int y, int nextX, int nextY) {
        if (x == nextX) {
            if (y > nextY) {
                maze.getMatrix()[x][y].setWallTop(false);
            } else {
                maze.getMatrix()[nextX][nextY].setWallTop(false);
            }
        } else {
            if (x > nextX) {
                maze.getMatrix()[x][y].setWallLeft(false);
            } else {
                maze.getMatrix()[nextX][nextY].setWallLeft(false);
            }
        }
    }
    public void createMaze(Maze maze) {
        for (int i = 0; i < maze.getSize(); ++i) {
            for (int j = 0; j < maze.getSize(); ++j) {
                if (!maze.getMatrix()[j][i].isVisited()) {
                    dfsMaze(maze, j, i);
                }
            }
        }
    }
    public void dfsMaze(Maze maze, int x, int y) {
        Stack<Cell> stack = new Stack<>();
        stack.push(maze.getMatrix()[x][y]);
        maze.getMatrix()[x][y].setVisited(true);
        while (!stack.isEmpty()) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
            Cell thisCell = stack.pop();
            Cell nextCell = cellController.randomNextCell(maze, thisCell.getX(), thisCell.getY());
            if (nextCell != null) {
                stack.push(thisCell);
                updateWall(maze, thisCell.getX(), thisCell.getY(), nextCell.getX(), nextCell.getY());
                nextCell.setVisited(true);
                stack.push(nextCell);
            }
        }
    }
}
