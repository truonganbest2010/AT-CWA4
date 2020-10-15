package controller;

import java.awt.event.*;

import javax.swing.JButton;

import view.ConfigScreen;

public class ConfigListener implements ActionListener {

    private ConfigScreen panel;

    public ConfigListener(ConfigScreen panel){
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // JButton button = (JButton) e.getSource();

        if (panel.getEasyBtn().isSelected()) {
            panel.setMazeSize(10);
            panel.setPreferredSize(18);
        }
        else if (panel.getNormalBtn().isSelected()){
            panel.setMazeSize(15);
            panel.setPreferredSize(15);
        }
        else if (panel.getHardBtn().isSelected()){
            panel.setMazeSize(25);
            panel.setPreferredSize(10);
        }
    }
    
}