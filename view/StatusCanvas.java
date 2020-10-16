package view;

import java.awt.*;

import javax.swing.JPanel;

public class StatusCanvas extends JPanel {
    
    private MazePanel panel;
    public StatusCanvas(MazePanel panel){
        this.panel = panel;
        setBackground(Color.black);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        

    }


}