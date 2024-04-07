package ProMazeTech.interListener;

import ProMazeTech.Model.Cell;
import ProMazeTech.Model.Maze;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class Product {
    private GraphicsContext graphicsContext;
    private Maze maze;
    private Cell end;
    private AnimationTimer timer;
    private ListenerStop listenerStop;
    public Product(GraphicsContext graphicsContext, Maze maze, Cell end, AnimationTimer timer) {
        this.graphicsContext = graphicsContext;
        this.maze = maze;
        this.end = end;
        this.timer = timer;
    }

    public ListenerStop setListener(ListenerStop mListener) {
        this.listenerStop = mListener;
        return mListener;
    }
}
