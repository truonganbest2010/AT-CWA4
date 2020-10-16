package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block extends MazeComponents {

    private int preferredSize;
    private boolean line;

    public Block(int x, int y, int preferredSize, boolean line) {
        super(x, y);
        this.preferredSize = preferredSize;
        this.line = line;
    }
    public int getPreferredSize() {
        return preferredSize;
    }
    public boolean getLine(){
        return line;
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub
        g2.drawImage(getImage(), null, getX(), getY());

        if (line) {
            g2.setColor(Color.black);
            g2.drawRect(getX(), getY(), preferredSize, preferredSize);
        }
    }

}