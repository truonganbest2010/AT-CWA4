package model;

import java.awt.*;
import java.awt.Graphics2D;

public class ClockString extends MazeComponents {

    private String number;
    private Font fontS = new Font("Courier", Font.BOLD, 36);

    public ClockString(int x, int y, String number) {
        super(x, y);
        // TODO Auto-generated constructor stub
        this.number = number;
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub
        g2.setColor(Color.WHITE);
        g2.setFont(fontS);
        g2.drawString(number, getX(), getY());
    }
    
}