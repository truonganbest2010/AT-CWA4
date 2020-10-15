package view;

import javax.swing.JFrame;

import controller.ConfigListener;

import java.awt.*;
import javax.swing.*;

public class ConfigScreen {

    private JFrame window;
    private JRadioButton easyBtn = new JRadioButton("Easy");
    private JRadioButton normalBtn = new JRadioButton("Normal");
    private JRadioButton hardBtn = new JRadioButton("Hard");

    private int preferredSize; // each block size
    private int mazeSize; // maze x * y size


    public ConfigScreen(JFrame window){
        this.window = window;
        window.setTitle("Maze Game");
        preferredSize = 15; // default
        mazeSize = 15; // default
        normalBtn.setSelected(true);
    }

    public void init()
    {
        Container cp = window.getContentPane();
        JPanel settingPanel = new JPanel();
        settingPanel.setPreferredSize(new Dimension(300, 200));
        settingPanel.setLayout(new GridLayout(2, 1));
        cp.add(BorderLayout.CENTER, settingPanel);

        JPanel difficultyPanel = new JPanel();
                
            ButtonGroup difficultyGroup = new ButtonGroup();
            difficultyGroup.add(easyBtn);
            difficultyGroup.add(normalBtn);
            difficultyGroup.add(hardBtn);
        difficultyPanel.add(easyBtn);
        difficultyPanel.add(normalBtn);
        difficultyPanel.add(hardBtn);

        JButton mazeButton = new JButton("Maze Game");

        settingPanel.add(difficultyPanel);
        settingPanel.add(mazeButton);

        mazeButton.addActionListener(e -> {
            window.getContentPane().removeAll();
            var maze = new MazePanel(window);
            maze.init(preferredSize, mazeSize);

            window.pack();
            window.revalidate();
        });

        ConfigListener listener = new ConfigListener(this);
        easyBtn.addActionListener(listener);
        normalBtn.addActionListener(listener);
        hardBtn.addActionListener(listener);

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
    public void setMazeSize(int mazeSize) {
        this.mazeSize = mazeSize;
    }
    public void setPreferredSize(int preferredSize) {
        this.preferredSize = preferredSize;
    }
}