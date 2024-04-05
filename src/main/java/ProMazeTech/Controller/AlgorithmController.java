package ProMazeTech.Controller;
import ProMazeTech.Model.Maze;
import ProMazeTech.Model.Cell;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class AlgorithmController {
    public boolean finish = false;

    public ArrayList<Cell> getListNeighbour(Maze maze, int x, int y) {
        ArrayList<Cell> nextCellList = new ArrayList<>();
        if (x - 1 >= 0 && !maze.getMatrix()[x - 1][y].isVisitedPath() && !maze.getMatrix()[x][y].getWallLeft()) {
            nextCellList.add(maze.getMatrix()[x - 1][y]);
        }
        if (y - 1 >= 0 && !maze.getMatrix()[x][y - 1].isVisitedPath() && !maze.getMatrix()[x][y].getWallTop()) {
            nextCellList.add(maze.getMatrix()[x][y - 1]);
        }
        if (x + 1 < maze.getSize() && !maze.getMatrix()[x + 1][y].isVisitedPath() && !maze.getMatrix()[x + 1][y].getWallLeft()) {
            nextCellList.add(maze.getMatrix()[x + 1][y]);
        }
        if (y + 1 < maze.getSize() && !maze.getMatrix()[x][y + 1].isVisitedPath() && !maze.getMatrix()[x][y + 1].getWallTop()) {
            nextCellList.add(maze.getMatrix()[x][y + 1]);
        }
        return nextCellList;
    }
    public void dfs(Maze maze, Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
        if (maze.getMatrix()[start.getKey()][start.getValue()].isVisitedPath()) {
            finish = true;
            return;
        }
        Stack<Cell> stack = new Stack<>();
        stack.push(maze.getMatrix()[start.getKey()][start.getValue()]);
        while (!stack.isEmpty()) {
            try {
                Thread.sleep(3);
            } catch (Exception e) {
                System.out.println(e);
            }
            Cell thisCell = stack.pop();
            if (!thisCell.isVisitedPath()) {
                thisCell.setVisitedPath(true);
                if (thisCell.getX() == end.getKey() && thisCell.getY() == end.getValue()) {
                    finish = true;
                    return;
                }
                ArrayList<Cell> neighbour = getListNeighbour(maze, thisCell.getX(), thisCell.getY());
                for (Cell cell : neighbour) {
                    if (!cell.isVisitedPath()) {
                        cell.setParent(thisCell);
                        stack.push(cell);
                    }
                }
            }
        }
    }
    public void bfs(Maze maze, Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
        System.out.println("fd");
        if (maze.getMatrix()[start.getKey()][start.getValue()].isVisitedPath()) {
            finish = true;
            return;
        }
        ArrayDeque<Cell> queue = new ArrayDeque<>();
        queue.push(maze.getMatrix()[start.getKey()][start.getValue()]);
        while (!queue.isEmpty()) {
            Cell thisCell = queue.getLast();
            queue.removeLast();
            try {
                Thread.sleep(3);
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!thisCell.isVisitedPath()) {
                thisCell.setVisitedPath(true);
                if (thisCell.getX() == end.getKey() && thisCell.getY() == end.getValue()) {
                    finish = true;
                    return;
                }
                ArrayList<Cell> neighbour = getListNeighbour(maze, thisCell.getX(), thisCell.getY());
                for (Cell cell : neighbour) {
                    if (!cell.isVisitedPath()) {
                        cell.setParent(thisCell);
                        queue.push(cell);
                    }
                }
            }
        }
    }
}