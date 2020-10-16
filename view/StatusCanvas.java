package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.MazeRender;
import view.MazePanel.GameState;

public class StatusCanvas extends JPanel {
    
    private Font fontS = new Font("Courier", Font.BOLD, 14);
    private MazePanel panel;
    private ArrayList<MazeRender> clock = new ArrayList<>();

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
        else if (panel.getGameState() == GameState.PLAYING){
            for (var c: clock){
                c.render(g2);
            }
            g2.setColor(Color.white);
            g2.setFont(fontS);
            g2.drawString("Time left", getWidth()/7, getHeight()-getHeight()/4);
        }
        else if (panel.getGameState() == GameState.GAMEOVER){
            g2.setColor(new Color(255, 51, 51));
            g2.drawString("Game Over", getWidth()/10, getHeight()/2);
            repaint();
        }

    }

    public ArrayList<MazeRender> getClock() {
        return clock;
    }
}