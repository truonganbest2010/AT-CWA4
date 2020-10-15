package controller;

import javax.swing.*;

import model.Block;
import model.Character;
import model.images.ImageStore;
import view.MazePanel;
import view.ConfigScreen;
import view.MazePanel.GameState;

import java.awt.Color;
import java.awt.event.*;


public class MazePanelListener implements ActionListener, KeyListener {

    private MazePanel panel;
    private char[][] grid, moveable;
    private int playerPosX, playerPosY;
    private int winPosX, winPosY;
    private int gridDimensionX;
    private int gridDimensionY;
    private int preferredSize;

    private Block[][] block;
    private Character[][] character;
 
    public MazePanelListener(MazePanel panel){
        this.panel = panel;
        
    }

    @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JButton button = (JButton) e.getSource();

            if (button == panel.getNewGameBtn()){
                
                panel.getNewGameBtn().setEnabled(false);
                panel.setGameState(GameState.PLAYING);
                panel.getMazeCanvas().getBlock().clear();
                panel.getMazeCanvas().getCharacter().clear();
                panel.getMazeGen().newMaze();
                playerPosX = panel.getMazeGen().getPlayerPosX();
                playerPosY = panel.getMazeGen().getPlayerPosY();
                gridDimensionX = panel.getMazeGen().getGridDimensionX();
                gridDimensionY = panel.getMazeGen().getGridDimensionY();
                preferredSize = panel.getPreferredSize();
                grid = new char[gridDimensionX][gridDimensionY];
                moveable = new char[gridDimensionX][gridDimensionY];
                block = new Block[gridDimensionX][gridDimensionY];
                character = new Character[gridDimensionX][gridDimensionY];
                
                grid = panel.getMazeGen().getGrid();
                for (int y = 0; y < gridDimensionY; y++){
                    int dy = y * preferredSize;
                    for (int x = 0; x < gridDimensionX; x++){
                        int dx = x * preferredSize;
                        if (grid[x][y] == 'S'){
                            // panel.getMazeCanvas().getBlock().add(new Block(dx, dy, preferredSize, Color.blue));
                            block[x][y] = new Block(dx, dy, preferredSize, Color.black);
                            block[x][y].setImage(ImageStore.block);
                            panel.getMazeCanvas().getBlock().add(block[x][y]);
                        }
                        if (grid[x][y] == 'X'){
                            // panel.getMazeCanvas().getBlock().add(new Block(dx, dy, preferredSize, Color.black));
                            block[x][y] = new Block(dx, dy, preferredSize, Color.black);
                            block[x][y].setImage(ImageStore.block);
                            panel.getMazeCanvas().getBlock().add(block[x][y]);
                        }
                        if (grid[x][y] == 'W'){
                            panel.getMazeCanvas().getBlock().add(new Block(dx, dy, preferredSize, Color.red));
                        }
                        if (grid[x][y] == '*'){
                            grid[x][y] = ' ';
                            panel.getMazeCanvas().getBlock().add(new Block(dx, dy, preferredSize, Color.yellow));
                        }
                        if (grid[x][y] == ' ' || grid[x][y] == '*'){
                            moveable[x][y] = ' ';
                        }
                        else if (grid[x][y] == 'W') {
                            moveable[x][y] = 'W';
                        }
                        else moveable[x][y] = 'X';
                        // System.out.print(moveable[x][y] + "");
                    }
                    // System.out.println("");
                }
                // drawing player
                panel.getMazeCanvas().getCharacter().add(new Character(playerPosX*preferredSize, playerPosY*preferredSize, preferredSize, Color.cyan));
                

                panel.getMazeCanvas().repaint();
                // System.out.println("x");
            } 
            else if (button == panel.getConfigBtn()){
                JFrame window = panel.getWindow();
                window.getContentPane().removeAll();
                var menu = new ConfigScreen(window);
                menu.init();
                window.pack();
                window.revalidate();
            }
            
        }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        var key = e.getKeyCode();


        switch (key){
            case KeyEvent.VK_LEFT:
                // System.out.println("left");
                if (moveable[playerPosX-1][playerPosY] == 'X'){
                    return;
                } else {
                    int dx = (playerPosX-1) * preferredSize;
                    int dy = (playerPosY) * preferredSize;
                    panel.getMazeCanvas().getCharacter().clear();
                    panel.getMazeCanvas().getCharacter().add(new Character(dx, dy, preferredSize, Color.cyan));
                    playerPosX -= 1;
                }

                break;


            case KeyEvent.VK_RIGHT:
                // System.out.println("right");
                if (moveable[playerPosX+1][playerPosY] == 'X'){
                    return;
                } else {
                    int dx = (playerPosX+1) * preferredSize;
                    int dy = (playerPosY) * preferredSize;
                    panel.getMazeCanvas().getCharacter().clear();
                    panel.getMazeCanvas().getCharacter().add(new Character(dx, dy, preferredSize, Color.cyan));
                    playerPosX += 1;
                }

                break;


            case KeyEvent.VK_UP:
                // System.out.println("up");
                if (moveable[playerPosX][playerPosY-1] == 'X'){
                    return;
                } else {
                    int dx = (playerPosX) * preferredSize;
                    int dy = (playerPosY-1) * preferredSize;
                    panel.getMazeCanvas().getCharacter().clear();
                    panel.getMazeCanvas().getCharacter().add(new Character(dx, dy, preferredSize, Color.cyan));
                    playerPosY -= 1;
                }

                break;


            case KeyEvent.VK_DOWN:
                // System.out.println("down");
                if (moveable[playerPosX][playerPosY+1] == 'X'){
                    return;
                } else {
                    int dx = (playerPosX) * preferredSize;
                    int dy = (playerPosY+1) * preferredSize;
                    panel.getMazeCanvas().getCharacter().clear();
                    panel.getMazeCanvas().getCharacter().add(new Character(dx, dy, preferredSize, Color.cyan));
                    if (moveable[playerPosX][playerPosY+1] == 'W') {
                        panel.setGameState(GameState.WIN);
                        panel.getNewGameBtn().setEnabled(true);
                    }
                    playerPosY += 1;
                }

                break;
            
            
        }
        panel.getMazeCanvas().repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    
    
}