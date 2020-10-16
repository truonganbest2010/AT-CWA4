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

    private JRadioButton brickBtn = new JRadioButton("Castle");
    private JRadioButton treeBtn = new JRadioButton("Forest");
    private JRadioButton ironBtn = new JRadioButton("Factory");
    private JRadioButton fenceBtn = new JRadioButton("Neighborhood");

    private JCheckBox drawLineCheck = new JCheckBox("Line");
    private boolean line;

    private JRadioButton marioBtn = new JRadioButton("Mario");
    private JRadioButton charmanderBtn = new JRadioButton("Charmander");
    private JRadioButton magnetoBtn = new JRadioButton("Magneto");
    private JTextField playerName = new JTextField(20);

    private int preferredSize; // each block size
    private int mazeSize; // maze x * y size
    private BufferedImage style;
    private BufferedImage character;


    public ConfigScreen(JFrame window){
        this.window = window;
        window.setTitle("Maze Game");
        preferredSize = 18; // default
        mazeSize = 10; // default
        line = false;
        int scale = 7;
        style = ImageStore.readImage("model/images/blockmodel/brick.jpg", preferredSize, preferredSize);
        character = ImageStore.readImage("model/images/charactermodel/mario.png", preferredSize+scale, preferredSize+scale);
        window.setResizable(false);
    }

    public void init()
    {
        Container cp = window.getContentPane();
        JPanel settingPanel = new JPanel();
        settingPanel.setPreferredSize(new Dimension(500, 300));
        settingPanel.setLayout(new GridLayout(5, 1));
        cp.add(BorderLayout.CENTER, settingPanel);

        JPanel difficultyPanel = new JPanel();
        TitledBorder diffBorder = BorderFactory.createTitledBorder("Difficulty");
        difficultyPanel.setBorder(diffBorder);
        easyBtn.setSelected(true);
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
            mapStyleGroup.add(ironBtn);
            mapStyleGroup.add(fenceBtn);
        mapStylePanel.add(brickBtn);
        mapStylePanel.add(treeBtn);
        mapStylePanel.add(ironBtn);
        mapStylePanel.add(fenceBtn);
        mapStylePanel.add(drawLineCheck);

        JPanel characterPanel = new JPanel();
        TitledBorder characterBorder = BorderFactory.createTitledBorder("Character");
        characterPanel.setBorder(characterBorder);
        marioBtn.setSelected(true);
            ButtonGroup characterGroup = new ButtonGroup();
            characterGroup.add(marioBtn);
            characterGroup.add(charmanderBtn);
            characterGroup.add(magnetoBtn);
        characterPanel.add(marioBtn);
        characterPanel.add(charmanderBtn);
        characterPanel.add(magnetoBtn);
        

        JPanel playerNamePanel = new JPanel();
        TitledBorder nameBorder = BorderFactory.createTitledBorder("Player Name");
        playerNamePanel.setBorder(nameBorder);
        playerNamePanel.add(playerName);


        settingPanel.add(mapStylePanel);
        settingPanel.add(characterPanel);
        settingPanel.add(playerNamePanel);
        settingPanel.add(difficultyPanel);
        
        JButton mazeButton = new JButton("Maze Game");
        settingPanel.add(mazeButton);
        mazeButton.addActionListener(e -> {
            window.getContentPane().removeAll();
            var maze = new MazePanel(window);
            maze.init(preferredSize, mazeSize, style, character, line, playerName.getText());

            window.pack();
            window.revalidate();
        });

        ConfigListener listener = new ConfigListener(this);

        easyBtn.addActionListener(listener);
        normalBtn.addActionListener(listener);
        hardBtn.addActionListener(listener);

        treeBtn.addActionListener(listener);
        brickBtn.addActionListener(listener);
        ironBtn.addActionListener(listener);
        fenceBtn.addActionListener(listener);
        drawLineCheck.addActionListener(listener);

        marioBtn.addActionListener(listener);
        charmanderBtn.addActionListener(listener);
        magnetoBtn.addActionListener(listener);

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
    public JRadioButton getFenceBtn() {
        return fenceBtn;
    }
    public JRadioButton getIronBtn() {
        return ironBtn;
    }
    public JCheckBox getDrawLineCheck() {
        return drawLineCheck;
    }
    public void setLine(boolean line) {
        this.line = line;
    }

    public JRadioButton getMarioBtn() {
        return marioBtn;
    }
    public JRadioButton getCharmanderBtn() {
        return charmanderBtn;
    }
    public JRadioButton getMagnetoBtn() {
        return magnetoBtn;
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