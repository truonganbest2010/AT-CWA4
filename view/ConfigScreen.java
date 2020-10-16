package view;

import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import controller.ConfigListener;
import model.images.ImageStore;
import java.awt.image.BufferedImage;


import java.awt.*;
import javax.swing.*;

public class ConfigScreen {

    private JFrame window;
    private JRadioButton easyBtn = new JRadioButton("Easy");
    private JRadioButton normalBtn = new JRadioButton("Normal");
    private JRadioButton hardBtn = new JRadioButton("Hard");

    private JRadioButton brickBtn = new JRadioButton("Brick");
    private JRadioButton treeBtn = new JRadioButton("Tree");

    private JRadioButton marioBtn = new JRadioButton("Mario");
    private JRadioButton knightBtn = new JRadioButton("Knight");
    private JRadioButton zombieBtn = new JRadioButton("Zombie");

    private int preferredSize; // each block size
    private int mazeSize; // maze x * y size
    private BufferedImage style;
    private BufferedImage character;


    public ConfigScreen(JFrame window){
        this.window = window;
        window.setTitle("Maze Game");
        preferredSize = 15; // default
        mazeSize = 15; // default

        int scale = 7;
        style = ImageStore.readImage("model/images/blockmodel/brick.jpg", preferredSize, preferredSize);
        character = ImageStore.readImage("model/images/charactermodel/mario.png", preferredSize+scale, preferredSize+scale);
    }

    public void init()
    {
        Container cp = window.getContentPane();
        JPanel settingPanel = new JPanel();
        settingPanel.setPreferredSize(new Dimension(400, 200));
        settingPanel.setLayout(new GridLayout(4, 1));
        cp.add(BorderLayout.CENTER, settingPanel);

        JPanel difficultyPanel = new JPanel();
        TitledBorder diffBorder = BorderFactory.createTitledBorder("Difficulty");
        difficultyPanel.setBorder(diffBorder);
        normalBtn.setSelected(true);
            ButtonGroup difficultyGroup = new ButtonGroup();
            difficultyGroup.add(easyBtn);
            difficultyGroup.add(normalBtn);
            difficultyGroup.add(hardBtn);
        difficultyPanel.add(easyBtn);
        difficultyPanel.add(normalBtn);
        difficultyPanel.add(hardBtn);

        JPanel mapStylePanel = new JPanel();
        TitledBorder styleBorder = BorderFactory.createTitledBorder("Map Style");
        mapStylePanel.setBorder(styleBorder);
        brickBtn.setSelected(true);
            ButtonGroup mapStyleGroup = new ButtonGroup();
            mapStyleGroup.add(brickBtn);
            mapStyleGroup.add(treeBtn);
        mapStylePanel.add(brickBtn);
        mapStylePanel.add(treeBtn);

        JPanel characterPanel = new JPanel();
        TitledBorder characterBorder = BorderFactory.createTitledBorder("Character");
        characterPanel.setBorder(characterBorder);
        marioBtn.setSelected(true);
            ButtonGroup characterGroup = new ButtonGroup();
            characterGroup.add(marioBtn);
            characterGroup.add(knightBtn);
            characterGroup.add(zombieBtn);
        characterPanel.add(marioBtn);
        characterPanel.add(knightBtn);
        characterPanel.add(zombieBtn);

        
        settingPanel.add(mapStylePanel);
        settingPanel.add(characterPanel);
        settingPanel.add(difficultyPanel);
        
        JButton mazeButton = new JButton("Maze Game");
        settingPanel.add(mazeButton);
        mazeButton.addActionListener(e -> {
            window.getContentPane().removeAll();
            var maze = new MazePanel(window);
            maze.init(preferredSize, mazeSize, style, character);

            window.pack();
            window.revalidate();
        });

        ConfigListener listener = new ConfigListener(this);

        easyBtn.addActionListener(listener);
        normalBtn.addActionListener(listener);
        hardBtn.addActionListener(listener);

        treeBtn.addActionListener(listener);
        brickBtn.addActionListener(listener);

        marioBtn.addActionListener(listener);
        knightBtn.addActionListener(listener);
        zombieBtn.addActionListener(listener);

    }
    public JRadioButton getEasyBtn() {
        return easyBtn;
    }
    public JRadioButton getNormalBtn() {
        return normalBtn;
    }
    public JRadioButton getHardBtn() {
        return hardBtn;
    }
    public JRadioButton getBrickBtn() {
        return brickBtn;
    }
    public JRadioButton getTreeBtn() {
        return treeBtn;
    }
    public JRadioButton getMarioBtn() {
        return marioBtn;
    }
    public JRadioButton getKnightBtn() {
        return knightBtn;
    }
    public JRadioButton getZombieBtn() {
        return zombieBtn;
    }
    public void setStyle(BufferedImage style) {
        this.style = style;
    }
    public void setCharacter(BufferedImage character) {
        this.character = character;
    }
    public void setMazeSize(int mazeSize) {
        this.mazeSize = mazeSize;
    }
    public int getPreferredSize() {
        return preferredSize;
    }
    public void setPreferredSize(int preferredSize) {
        this.preferredSize = preferredSize;
    }
}