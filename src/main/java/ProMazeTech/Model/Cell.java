package ProMazeTech.Model;

public class Cell {
    private int x;
    private int y;
    private boolean[] walls = {true, true}; // top left
    private boolean visited = false;
    private boolean visitedPath = false;
    private Cell parent = null;

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public boolean isVisitedPath() {
        return visitedPath;
    }

    public void setVisitedPath(boolean visitedPath) {
        this.visitedPath = visitedPath;
    }

    public void setWallTop(boolean wall) {
        this.walls[0] = wall;
    }
    public boolean getWallTop() {
        return this.walls[0];
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setWallLeft(boolean wall) {
        this.walls[1] = wall;
    }
    public boolean getWallLeft() {
        return this.walls[1];
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

