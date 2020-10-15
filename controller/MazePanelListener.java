package controller;

import javax.swing.*;

import model.Block;
import view.MazePanel;

import java.awt.Color;
import java.awt.event.*;


public class MazePanelListener implements ActionListener, KeyListener {

    private MazePanel panel;

    public MazePanelListener(MazePanel panel){
        this.panel = panel;
    }

    @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JButton button = (JButton) e.getSource();
            if (button == panel.getNewGameBtn()){
                int gridDimensionX = panel.getMazeGen().getGridDimensionX();
                int gridDimensionY = panel.getMazeGen().getGridDimensionY();
                int preferredSize = panel.getPreferredSize();
                char[][] grid = new char[gridDimensionX][gridDimensionY];
                grid = panel.getMazeGen().getGrid();
                

                for (int y = 0; y < gridDimensionY; y++){
                    int dy = y * preferredSize;
                    for (int x = 0; x < gridDimensionX; x++){
                        int dx = x * preferredSize;
                        if (grid[x][y] == 'S'){
                            panel.getMazeCanvas().getMaze().add(new Block(dx, dy, preferredSize, Color.blue));
                        }
                        if (grid[x][y] == 'X'){
                            panel.getMazeCanvas().getMaze().add(new Block(dx, dy, preferredSize, Color.black));
                        }
                        if (grid[x][y] == '0'){
                            panel.getMazeCanvas().getMaze().add(new Block(dx, dy, preferredSize, Color.red));
                        }
                        if (grid[x][y] == '*'){
                            panel.getMazeCanvas().getMaze().add(new Block(dx, dy, preferredSize, Color.yellow));
                        }
                    }
                }
                panel.getMazeCanvas().repaint();
                System.out.println("x");
            } 
            else if (button == panel.getConfigBtn()){
                
            }
            


        }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    
    
}