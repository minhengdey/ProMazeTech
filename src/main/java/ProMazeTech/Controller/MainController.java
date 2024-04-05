package ProMazeTech.Controller;

import ProMazeTech.Model.Maze;
import ProMazeTech.View.DisplayPath;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;
import ProMazeTech.View.DisplayMaze;

public class MainController implements Initializable {
    @FXML
    TextField size;
    @FXML
    ComboBox<String> algorithm;
    @FXML
    AnchorPane anchorPane;
    ObservableList<String> listAlgorithm = FXCollections.observableArrayList("BFS", "DFS", "A*");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        algorithm.setItems(listAlgorithm);
    }
    public void SUBMIT(ActionEvent event) {
        AlgorithmController algorithmController = new AlgorithmController();
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext graphicsContext;
        Maze maze = new Maze();
        DisplayMaze displayMaze = new DisplayMaze();
        DisplayPath displayPath = new DisplayPath();
        int Size = Integer.parseInt(size.getText());
        String Algorithm = algorithm.getValue();
        maze.setSize(Size);
        int cellSize = 400 / maze.getSize();
        graphicsContext = canvas.getGraphicsContext2D();
        anchorPane.getChildren().add(graphicsContext.getCanvas());
        displayMaze.drawMaze(graphicsContext, maze);
        EventHandler<MouseEvent> mouseClickHandlerStart = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                displayMaze.stopDrawMaze();
                int x = (int) (mouseEvent.getX() / cellSize);
                int y = (int) (mouseEvent.getY() / cellSize);
                Pair<Integer, Integer> start = new Pair<>(x, y);
                graphicsContext.setFill(Color.RED);
                graphicsContext.fillRect(start.getKey() * cellSize, start.getValue() * cellSize, cellSize, cellSize);
                EventHandler<MouseEvent> mouseClickHandlerEnd = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int x1 = (int) (mouseEvent.getX() / cellSize);
                        int y1 = (int) (mouseEvent.getY() / cellSize);
                       Pair<Integer, Integer> end = new Pair<>(x1, y1);
                        graphicsContext.fillRect(end.getKey() * cellSize, end.getValue() * cellSize, cellSize, cellSize);
                        displayPath.drawPath(graphicsContext, maze, start, end, Algorithm);
                        if (algorithmController.finish) {
                            displayPath.stopDrawPath();
                            displayPath.drawLinePath(graphicsContext, maze.getMatrix()[end.getKey()][end.getValue()]);
                        }
                    }
                };
                anchorPane.setOnMouseClicked(mouseClickHandlerEnd);
            }
        };
        anchorPane.setOnMouseClicked(mouseClickHandlerStart);
    }
}
