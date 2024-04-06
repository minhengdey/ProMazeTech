package ProMazeTech.interListener;

import ProMazeTech.Model.Cell;
import ProMazeTech.Model.Maze;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public interface ListenerStop {
    public void stopDrawPath(GraphicsContext graphicsContext, Maze maze, Cell end, AnimationTimer timer);
}
