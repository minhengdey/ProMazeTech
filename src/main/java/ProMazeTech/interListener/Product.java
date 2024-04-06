package ProMazeTech.interListener;

import ProMazeTech.Model.Cell;
import ProMazeTech.Model.Maze;
import javafx.animation.AnimationTimer;
import ProMazeTech.View.DisplayPath;
import javafx.scene.canvas.GraphicsContext;

import java.lang.reflect.GenericArrayType;

public class Product {
    private GraphicsContext graphicsContext;
    private Maze maze;
    private Cell end;

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public void setEnd(Cell end) {
        this.end = end;
    }

    private AnimationTimer timer;

    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

    private ListenerStop mListener;
    public Product() {
    }


    public void setCheck(boolean check) {
        if (!check) {
            return;
        }
        if (this.mListener != null) {
            this.mListener.stopDrawPath(graphicsContext, maze, end, timer);
        }
    }

    public void setListener(ListenerStop mListener) {
        this.mListener = mListener;
    }
}
