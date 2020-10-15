package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block extends MazeComponents {

    private Color color;

    public Block(int x, int y, int preferredSize, Color color) {
        super(x, y, preferredSize);
        this.color = color;
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub
        g2.setColor(color);
        g2.fillRect(getX(), getY(), getPreferredSize(), getPreferredSize());
    }

}