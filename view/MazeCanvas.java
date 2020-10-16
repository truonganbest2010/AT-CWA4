package view;

import javax.swing.JPanel;

import model.MazeRender;
import view.MazePanel.GameState;

import java.awt.*;
import java.util.ArrayList;

public class MazeCanvas extends JPanel {

    private MazePanel panel;

    private ArrayList<MazeRender> block = new ArrayList<>();
    private ArrayList<MazeRender> character = new ArrayList<>();
    private Font fontS = new Font("Courier", Font.BOLD, 36);

    public MazeCanvas(MazePanel panel, int mazeSize, int preferredSize){
        this.panel = panel;
        setPreferredSize(new Dimension(preferredSize*mazeSize*4, mazeSize*preferredSize*2+preferredSize));
        setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        

        if (panel.getGameState() == GameState.READY) {
            setBackground(Color.BLACK);
            g2.setColor(Color.WHITE);
            g2.setFont(fontS);
            g2.drawString("Maze", getWidth()/2, getHeight()/3);
            g2.drawString("Click <Start> to play", getWidth()/3, getHeight()/3 + getHeight()/5);
            repaint();
        }
        else if (panel.getGameState() == GameState.PLAYING){
            setBackground(Color.white);
            for (var b: block){
                b.render(g2);
            }
            for (var c: character) {
                c.render(g2);
            }
        }
        else if (panel.getGameState() == GameState.WIN){
            for (var b: block){
                b.render(g2);
            }
            for (var c: character) {
                c.render(g2);
            }
            g2.setColor(new Color(0, 200, 255, 96));
            g2.fillRect(0, 0, getWidth(), getHeight());
            
            g2.setFont(fontS);
            g2.drawString("Good Job", getWidth()/4, getHeight()/2);
            repaint();
        }
        else if (panel.getGameState() == GameState.GAMEOVER){ // GAME OVER
            for (var b: block){
                b.render(g2);
            }
            for (var c: character) {
                c.render(g2);
            }
            g2.setColor(new Color(255, 51, 51, 96));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setFont(fontS);
            g2.drawString("Game Over", getWidth()/4, getHeight()/2);
            repaint();
        }
        
    }

    public ArrayList<MazeRender> getBlock() {
        return block;
    }
    public ArrayList<MazeRender> getCharacter() {
        return character;
    }

}