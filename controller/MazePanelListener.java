package controller;

import javax.swing.*;

import model.Block;
import model.Character;
import model.ClockString;
import model.images.ImageStore;
import view.MazePanel;
import view.ConfigScreen;
import view.MazePanel.GameState;

import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;


public class MazePanelListener implements ActionListener, KeyListener {

    private MazePanel panel;
    private char[][] grid, moveable;
    private int playerPosX, playerPosY;
    private int gridDimensionX;
    private int gridDimensionY;
    private int preferredSize;

    private Timer timer;
    private int delay;
    private int period;
    private int interval;

    private Block block;
    private Character character;

    public MazePanelListener(MazePanel panel){
        this.panel = panel;
    }

    @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JButton button = (JButton) e.getSource();

            if (button == panel.getNewGameBtn()){
                /** Toggle buttons */
                panel.getNewGameBtn().setEnabled(false);
                panel.getStopBtn().setEnabled(true);
                panel.getConfigBtn().setEnabled(false);
                /**  */
                panel.setGameState(GameState.PLAYING);
                panel.getMazeCanvas().getBlock().clear();
                panel.getMazeCanvas().getCharacter().clear();
                panel.getStatusCanvas().getClock().clear();
                panel.getMazeGen().newMaze();
                /** initialize player position and map */
                playerPosX = panel.getMazeGen().getPlayerPosX();
                playerPosY = panel.getMazeGen().getPlayerPosY();
                gridDimensionX = panel.getMazeGen().getGridDimensionX();
                gridDimensionY = panel.getMazeGen().getGridDimensionY();
                preferredSize = panel.getPreferredSize();
                interval = 90;
                delay = 1000;
                period = 1000;

                grid = new char[gridDimensionX][gridDimensionY];
                moveable = new char[gridDimensionX][gridDimensionY];
                
                grid = panel.getMazeGen().getGrid();
                for (int y = 0; y < gridDimensionY; y++){
                    int dy = y * preferredSize;
                    for (int x = 0; x < gridDimensionX; x++){
                        int dx = x * preferredSize;
                        if (grid[x][y] == 'S'){
                            block = new Block(dx, dy, preferredSize, false);
                            block.setImage(ImageStore.door);
                            panel.getMazeCanvas().getBlock().add(block);
                        }
                        if (grid[x][y] == 'X'){
                            block = new Block(dx, dy, preferredSize, panel.getLine());
                            block.setImage(panel.getStyle());
                            panel.getMazeCanvas().getBlock().add(block);
                        }
                        if (grid[x][y] == 'W'){
                            block = new Block(dx, dy, preferredSize, false);
                            block.setImage(ImageStore.door);
                            panel.getMazeCanvas().getBlock().add(block);
                        }
                        if (grid[x][y] == '*'){
                            // grid[x][y] = ' ';
                            
                        }
                        if (grid[x][y] == ' ' || grid[x][y] == '*'){
                            moveable[x][y] = ' ';
                        }
                        else if (grid[x][y] == 'W') {
                            moveable[x][y] = 'W';
                        }
                        else moveable[x][y] = 'X';
                        // System.out.print(moveable[x][y] + "");
                        // System.out.print(grid[x][y] + "");
                    }
                    // System.out.println();
                }
                /* drawing player */
                character = new Character(playerPosX*preferredSize, playerPosY*preferredSize, panel.getName());
                character.setImage(panel.getCharacter());
                panel.getMazeCanvas().getCharacter().add(character);
                
                /** Initialize Clock */
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask(){
                    public void run(){
                        panel.getStatusCanvas().getClock().clear();
                        panel.getStatusCanvas().getClock().add(new ClockString(panel.getStatusCanvas().getWidth()/4, panel.getStatusCanvas().getHeight()/2, interval+""));
                        if (interval < 0){
                            timer.cancel();
                            panel.getStatusCanvas().getClock().clear();
                            panel.setGameState(GameState.GAMEOVER);
                            panel.getNewGameBtn().setEnabled(true);
                            panel.getStopBtn().setEnabled(false);
                            panel.getConfigBtn().setEnabled(true);
                            panel.getStatusCanvas().repaint();
                            panel.getMazeCanvas().repaint();
                        }   
                        else {
                            --interval;
                            panel.getStatusCanvas().repaint();
                        }
                        if (panel.getGameState() == GameState.WIN) {
                            timer.cancel();
                        }
                    }
                }, delay, period);

                /** Refresh canvases */
                panel.getMazeCanvas().repaint();
                panel.getStatusCanvas().repaint();
                // System.out.println("x");
            }

            else if (button == panel.getStopBtn()){
                panel.setGameState(GameState.GAMEOVER);
                panel.getStopBtn().setEnabled(false);
                panel.getNewGameBtn().setEnabled(true);
                panel.getConfigBtn().setEnabled(true);
                panel.getMazeCanvas().repaint();
                panel.getStatusCanvas().repaint();
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

        if (panel.getGameState() == GameState.PLAYING){
            switch (key){
                case KeyEvent.VK_LEFT:
                    // System.out.println("left");
                    if (moveable[playerPosX-1][playerPosY] == 'X'){
                        return;
                    } else {
                        panel.getMazeCanvas().getCharacter().clear();
                        int dx = (playerPosX-1) * preferredSize;
                        int dy = (playerPosY) * preferredSize;
                        character = new Character(dx, dy, panel.getName());
                        character.setImage(panel.getCharacter());
                        panel.getMazeCanvas().getCharacter().add(character);
                        playerPosX -= 1;
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    // System.out.println("right");
                    if (moveable[playerPosX+1][playerPosY] == 'X'){
                        return;
                    } else {
                        panel.getMazeCanvas().getCharacter().clear();
                        int dx = (playerPosX+1) * preferredSize;
                        int dy = (playerPosY) * preferredSize;
                        character = new Character(dx, dy, panel.getName());
                        character.setImage(panel.getCharacter());
                        panel.getMazeCanvas().getCharacter().add(character);
                        playerPosX += 1;
                    }
                    break;

                case KeyEvent.VK_UP:
                    // System.out.println("up");
                    if (moveable[playerPosX][playerPosY-1] == 'X'){
                        return;
                    } else {
                        panel.getMazeCanvas().getCharacter().clear();
                        int dx = (playerPosX) * preferredSize;
                        int dy = (playerPosY-1) * preferredSize;
                        character = new Character(dx, dy, panel.getName());
                        character.setImage(panel.getCharacter());
                        panel.getMazeCanvas().getCharacter().add(character);
                        playerPosY -= 1;
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    // System.out.println("down");
                    if (moveable[playerPosX][playerPosY+1] == 'X'){
                        return;
                    } else {
                        panel.getMazeCanvas().getCharacter().clear();
                        int dx = (playerPosX) * preferredSize;
                        int dy = (playerPosY+1) * preferredSize;
                        character = new Character(dx, dy, panel.getName());
                        character.setImage(panel.getCharacter());
                        panel.getMazeCanvas().getCharacter().add(character);
                        if (moveable[playerPosX][playerPosY+1] == 'W') {
                            panel.setGameState(GameState.WIN);
                            panel.getNewGameBtn().setEnabled(true);
                            panel.getStopBtn().setEnabled(false);
                            panel.getConfigBtn().setEnabled(true);
                        }
                        playerPosY += 1;
                    }

                    break;  
            }
            panel.getMazeCanvas().repaint();
            panel.getStatusCanvas().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    
    
}