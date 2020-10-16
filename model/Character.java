package model;

import java.awt.Graphics2D;
import java.awt.*;

public class Character extends MazeComponents {

    private String name;

    public Character(int x, int y, String name) {
        super(x, y);
        // TODO Auto-generated constructor stub
        this.name = name;
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub
        g2.drawImage(getImage(), null, getX(), getY());
        g2.setColor(Color.red);
        g2.drawString(name, getX(), getY());
    }
    
}