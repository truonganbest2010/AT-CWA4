package view;

import java.awt.*;

import javax.swing.JPanel;

import view.MazePanel.GameState;

public class StatusCanvas extends JPanel {
    
    private Font fontS = new Font("Courier", Font.BOLD, 14);
    private MazePanel panel;
    public StatusCanvas(MazePanel panel){
        this.panel = panel;
        setBackground(Color.black);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(fontS);


        if (panel.getGameState() == GameState.WIN){
            g2.setColor(new Color(0, 200, 255));
            g2.drawString("Good Job", getWidth()/10, getHeight()/2);
            repaint();
        }
        else if (panel.getGameState() == GameState.GAMEOVER){
            g2.setColor(new Color(255, 51, 51));
            g2.drawString("Game Over", getWidth()/10, getHeight()/2);
            repaint();
        }

    }


}