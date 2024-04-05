package ProMazeTech.Model;

public class Maze {

    private int size;
    private Cell[][] matrix = new Cell[400][400];

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Cell[][] getMatrix() {
        return matrix;
    }
    public Maze() {
        for (int i = 0; i < 400; ++i) {
            for (int j = 0; j < 400; ++j) {
                matrix[j][i] = new Cell();
                matrix[j][i].setX(j);
                matrix[j][i].setY(i);
                matrix[j][i].setVisited(false);
            }
        }
    }
}

