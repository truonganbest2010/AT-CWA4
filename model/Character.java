package model;

import java.awt.Graphics2D;
import java.awt.*;

public class Character extends MazeComponents {

    private Color color;

    public Character(int x, int y, int preferredSize, Color color) {
        super(x, y, preferredSize);
        // TODO Auto-generated constructor stub
        this.color = color;
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub
        g2.drawImage(getImage(), null, getX(), getY());
        g2.setColor(color);
        // g2.fillRect(getX(), getY(), getPreferredSize(), getPreferredSize());
    }
    
}