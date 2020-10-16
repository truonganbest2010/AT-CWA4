package model;
import java.awt.image.BufferedImage;

public abstract class MazeComponents implements MazeRender {
    
    private int x;
    private int y;
    private BufferedImage image;

    public MazeComponents(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}