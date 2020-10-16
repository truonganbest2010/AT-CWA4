package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.MazePanelListener;
import model.MazeGenerator;
import java.awt.image.BufferedImage;
import java.awt.*;

public class MazePanel {

    public enum GameState {
        READY, PLAYING, WIN, GAMEOVER
    }
    
    private JFrame window;
    private MazeCanvas mazeCanvas;
    private MazeGenerator mazeGen;

    private BufferedImage style;
    private BufferedImage character;


    private int preferredSize; // each block size
    private int mazeSize; // map x * y

    private JButton newGameBtn = new JButton("Start");
    private JButton configBtn = new JButton("Settings");

    private GameState gameState = GameState.READY;

    public MazePanel(JFrame window){
        this.window = window;
        
        window.setResizable(false);
        window.setTitle("Maze Game");
    }

    public void init(int preferredSize, int mazeSize, BufferedImage style, BufferedImage character){
        this.mazeSize = mazeSize; 
        this.preferredSize = preferredSize;
        this.style = style;
        this.character = character;

        mazeGen = new MazeGenerator(mazeSize);
        mazeCanvas = new MazeCanvas(this, mazeSize, preferredSize);

        Container cp = window.getContentPane();
        
        TitledBorder canvasBorder = BorderFactory.createTitledBorder("");
        mazeCanvas.setBorder(canvasBorder);
        cp.add(BorderLayout.CENTER, mazeCanvas);

        JPanel rightPanel = new JPanel();
        cp.add(BorderLayout.EAST, rightPanel);
        rightPanel.setLayout(new GridLayout(2, 1));

        rightPanel.add(newGameBtn);
        rightPanel.add(configBtn);

        MazePanelListener listener = new MazePanelListener(this);
        newGameBtn.addActionListener(listener);
        configBtn.addActionListener(listener);
        mazeCanvas.addKeyListener(listener);
        mazeCanvas.requestFocusInWindow();
        mazeCanvas.setFocusable(true);

        newGameBtn.setFocusable(false);
        configBtn.setFocusable(false);
    }

    public JFrame getWindow() {
        return window;
    }
    public MazeCanvas getMazeCanvas() {
        return mazeCanvas;
    }
    public MazeGenerator getMazeGen() {
        return mazeGen;
    }
    public int getPreferredSize() {
        return preferredSize;
    }
    public JButton getNewGameBtn() {
        return newGameBtn;
    }
    public JButton getConfigBtn() {
        return configBtn;
    }
    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public BufferedImage getStyle() {
        return style;
    }
    public BufferedImage getCharacter() {
        return character;
    }

}