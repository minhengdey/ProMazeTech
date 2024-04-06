package ProMazeTech.View;

import ProMazeTech.Model.Cell;
import ProMazeTech.Model.Maze;
import ProMazeTech.Controller.MazeController;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DisplayMaze {
    MazeController mazeController = new MazeController();
    boolean running = true;
    AnimationTimer timer;
    Thread thread;
    public void stopDrawMaze() {
        timer.stop();
        running = false;
    }
    public void drawMaze(GraphicsContext gc, Maze maze) {
        int cellSize = 400 / maze.getSize();
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.DEEPPINK);
        timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                for (int i = 0; i < maze.getSize(); ++i) {
                    for (int j = 0; j < maze.getSize(); ++j) {
                        Cell cell = maze.getMatrix()[j][i];
                        gc.fillRect(cell.getX() * cellSize, cell.getY() * cellSize, cellSize, cellSize);
                        if (cell.getWallTop()) {
                            gc.strokeLine(cell.getX() * cellSize, cell.getY() * cellSize, cellSize * (cell.getX() + 1), cell.getY() * cellSize);
                        }
                        if (cell.getWallLeft()) {
                            gc.strokeLine(cell.getX() * cellSize, cell.getY() * cellSize, cell.getX() * cellSize, cellSize * (cell.getY() + 1));
                        }
                        if (cell.getX() == maze.getSize() - 1) {
                            gc.strokeLine(cellSize * (cell.getX() + 1), cell.getY() * cellSize, cellSize * (cell.getX() + 1), cellSize * (cell.getY() + 1));
                        }
                        if (cell.getY() == maze.getSize() - 1) {
                            gc.strokeLine(cell.getX() * cellSize, cellSize * (cell.getY() + 1), cellSize * (cell.getX() + 1), cellSize * (cell.getY() + 1));
                        }
                    }
                }
            }
        };
        thread = new Thread(() -> {
            mazeController.createMaze(maze);
        });
        timer.start();
        thread.start();
    }
}
