package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block extends MazeComponents {

    private int preferredSize;

    public Block(int x, int y, int preferredSize) {
        super(x, y);
        this.preferredSize = preferredSize;
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub
        g2.drawImage(getImage(), null, getX(), getY());
        g2.setColor(Color.black);
        // g2.drawRect(getX(), getY(), preferredSize, preferredSize);
    }

}