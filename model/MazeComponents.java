package model;

public abstract class MazeComponents implements MazeRender {
    
    private int x;
    private int y;
    private int preferredSize;

    public MazeComponents(int x, int y, int preferredSize) {
        this.x = x;
        this.y = y;
        this.preferredSize = preferredSize;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getPreferredSize() {
        return preferredSize;
    }
}