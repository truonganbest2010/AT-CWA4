package view;

import javax.swing.JFrame;

import java.awt.*;
import javax.swing.*;

public class MenuScreen {
    private JFrame window;

    public MenuScreen(JFrame window){
        this.window = window;
    }

    public void init()
    {
        Container cp = window.getContentPane();
        JPanel panel = new JPanel();

        panel.setPreferredSize(new Dimension(400, 200));
        panel.setLayout(new GridLayout(2, 1));

        JButton idemoButton = new JButton("Interface Demo");
        JButton drawingButton = new JButton("Drawing Demo");
        panel.add(idemoButton);
        panel.add(drawingButton);

        cp.add(BorderLayout.CENTER, panel);

        idemoButton.addActionListener(e -> {
           
        });

        drawingButton.addActionListener(e -> {
            
        });

    }
}