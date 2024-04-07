package ProMazeTech.View;

import ProMazeTech.Model.Cell;
import ProMazeTech.Model.Maze;
import ProMazeTech.interListener.ListenerStop;
import ProMazeTech.interListener.Product;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import ProMazeTech.Controller.AlgorithmController;

public class DisplayPath {
    int cellSize;
    AnimationTimer timer;
    Thread thread;
    AlgorithmController algorithmController = new AlgorithmController();
    public void drawPath(GraphicsContext graphicsContext, Maze maze, Pair<Integer, Integer> start, Pair<Integer, Integer> end, String algorithm) {
        cellSize = 400 / maze.getSize();
        graphicsContext.setFill(Color.LIGHTPINK);
        timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                for (int i = 0; i < maze.getSize(); ++i) {
                    for (int j = 0; j < maze.getSize(); ++j) {
                        if (maze.getMatrix()[j][i].isVisitedPath() && !start.equals(new Pair<>(j, i)) && !end.equals(new Pair<>(j, i))) {
                            graphicsContext.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                        }
                        Cell cell = maze.getMatrix()[j][i];
                        if (cell.getWallTop()) {
                            graphicsContext.strokeLine(cell.getX() * cellSize, cell.getY() * cellSize, cellSize * (cell.getX() + 1), cell.getY() * cellSize);
                        }
                        if (cell.getWallLeft()) {
                            graphicsContext.strokeLine(cell.getX() * cellSize, cell.getY() * cellSize, cell.getX() * cellSize, cellSize * (cell.getY() + 1));
                        }
                        if (cell.getX() == maze.getSize() - 1) {
                            graphicsContext.strokeLine(cellSize * (cell.getX() + 1), cell.getY() * cellSize, cellSize * (cell.getX() + 1), cellSize * (cell.getY() + 1));
                        }
                        if (cell.getY() == maze.getSize() - 1) {
                            graphicsContext.strokeLine(cell.getX() * cellSize, cellSize * (cell.getY() + 1), cellSize * (cell.getX() + 1), cellSize * (cell.getY() + 1));
                        }
                    }
                }
            }
        };
        if (algorithm.equals("BFS")) {
            thread = new Thread(() -> {
                Product product = new Product(graphicsContext, maze, maze.getMatrix()[end.getKey()][end.getValue()], timer);
                algorithmController.bfs(graphicsContext, maze, start, end, timer, product.setListener(new ListenerStop() {
                    @Override
                    public void stopDrawPath(GraphicsContext graphicsContext, Maze maze, Cell cell, AnimationTimer timer) {
                        Cell parCell = cell.getParent();
                        while (parCell != null) {
                            int cellSize = 400 / maze.getSize();
                            graphicsContext.setStroke(Color.BLACK);
                            if (cell.getX() == parCell.getX()) {
                                if (cell.getY() < parCell.getY()) {
                                    graphicsContext.strokeLine(cell.getX() * cellSize + cellSize / 2.0, cell.getY() * cellSize + cellSize / 2.0, parCell.getX() * cellSize + cellSize / 2.0, parCell.getY() * cellSize + cellSize / 2.0);
                                } else {
                                    graphicsContext.strokeLine(parCell.getX() * cellSize + cellSize / 2.0, parCell.getY() * cellSize + cellSize / 2.0, cell.getX() * cellSize + cellSize / 2.0, cell.getY() * cellSize + cellSize / 2.0);
                                }
                            } else {
                                if (cell.getX() < parCell.getX()) {
                                    graphicsContext.strokeLine(cell.getX() * cellSize + cellSize / 2.0, cell.getY() * cellSize + cellSize / 2.0, parCell.getX() * cellSize + cellSize / 2.0, parCell.getY() * cellSize + cellSize / 2.0);
                                } else {
                                    graphicsContext.strokeLine(parCell.getX() * cellSize + cellSize / 2.0, parCell.getY() * cellSize + cellSize / 2.0, cell.getX() * cellSize + cellSize / 2.0, cell.getY() * cellSize + cellSize / 2.0);
                                }
                            }
                            cell = parCell;
                            parCell = cell.getParent();
                        }
                        graphicsContext.setFill(Color.RED);
                        graphicsContext.fillRect(start.getKey() * cellSize, start.getValue() * cellSize, cellSize, cellSize);
                        graphicsContext.fillRect(end.getKey() * cellSize, end.getValue() * cellSize, cellSize, cellSize);
                        timer.stop();
                    }
                }));
            });
        } else {
            thread = new Thread(() -> {
                Product product = new Product(graphicsContext, maze, maze.getMatrix()[end.getKey()][end.getValue()], timer);
                algorithmController.dfs(graphicsContext, maze, start, end, timer, product.setListener(new ListenerStop() {
                    @Override
                    public void stopDrawPath(GraphicsContext graphicsContext, Maze maze, Cell cell, AnimationTimer timer) {
                        Cell parCell = cell.getParent();
                        while (parCell != null) {
                            graphicsContext.setStroke(Color.BLACK);
                            if (cell.getX() == parCell.getX()) {
                                if (cell.getY() < parCell.getY()) {
                                    graphicsContext.strokeLine(cell.getX() * cellSize + cellSize / 2.0, cell.getY() * cellSize + cellSize / 2.0, parCell.getX() * cellSize + cellSize / 2.0, parCell.getY() * cellSize + cellSize / 2.0);
                                } else {
                                    graphicsContext.strokeLine(parCell.getX() * cellSize + cellSize / 2.0, parCell.getY() * cellSize + cellSize / 2.0, cell.getX() * cellSize + cellSize / 2.0, cell.getY() * cellSize + cellSize / 2.0);
                                }
                            } else {
                                if (cell.getX() < parCell.getX()) {
                                    graphicsContext.strokeLine(cell.getX() * cellSize + cellSize / 2.0, cell.getY() * cellSize + cellSize / 2.0, parCell.getX() * cellSize + cellSize / 2.0, parCell.getY() * cellSize + cellSize / 2.0);
                                } else {
                                    graphicsContext.strokeLine(parCell.getX() * cellSize + cellSize / 2.0, parCell.getY() * cellSize + cellSize / 2.0, cell.getX() * cellSize + cellSize / 2.0, cell.getY() * cellSize + cellSize / 2.0);
                                }
                            }
                            cell = parCell;
                            parCell = cell.getParent();
                        }
                        graphicsContext.setFill(Color.RED);
                        graphicsContext.fillRect(start.getKey() * cellSize, start.getValue() * cellSize, cellSize, cellSize);
                        graphicsContext.fillRect(end.getKey() * cellSize, end.getValue() * cellSize, cellSize, cellSize);
                        timer.stop();
                    }
                }));
            });
        }
        timer.start();
        thread.start();
    }
}
