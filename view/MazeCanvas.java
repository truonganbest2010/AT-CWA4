package view;

import javax.swing.JPanel;

import model.MazeRender;

import java.awt.*;
import java.util.ArrayList;

public class MazeCanvas extends JPanel {

    private MazePanel panel;

    private ArrayList<MazeRender> maze = new ArrayList<>();

    public MazeCanvas(MazePanel panel, int mazeSize, int preferredSize){
        this.panel = panel;
        setPreferredSize(new Dimension(preferredSize*mazeSize*4, mazeSize*preferredSize*2+preferredSize));
        setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        
        for (var m: maze){
            m.render(g2);
        }
        
    }

    public ArrayList<MazeRender> getMaze() {
        return maze;
    }

}