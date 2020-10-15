package view;

import javax.swing.JFrame;

import java.awt.*;
import javax.swing.*;

public class MenuScreen {
    private JFrame window;

    public MenuScreen(JFrame window){
        this.window = window;
        window.setTitle("Settings");
    }

    public void init()
    {
        Container cp = window.getContentPane();
        JPanel panel = new JPanel();

        panel.setPreferredSize(new Dimension(400, 200));
        panel.setLayout(new GridLayout(2, 1));

        JButton mazeButton = new JButton("Maze Game");
        panel.add(mazeButton);

        cp.add(BorderLayout.CENTER, panel);

        mazeButton.addActionListener(e -> {
            window.getContentPane().removeAll();
            var maze = new MazePanel(window);
            maze.init();

            window.pack();
            window.revalidate();
        });


    }
}