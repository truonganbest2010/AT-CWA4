package controller;

import java.awt.event.*;

import javax.swing.JButton;

import model.images.ImageStore;
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

        int scale = 7;

        if (panel.getEasyBtn().isSelected()) {
            panel.setMazeSize(10);
            panel.setPreferredSize(18);
        }
        if (panel.getNormalBtn().isSelected()){
            panel.setMazeSize(15);
            panel.setPreferredSize(15);
        }
        if (panel.getHardBtn().isSelected()){
            panel.setMazeSize(22);
            panel.setPreferredSize(13);
        }

        if (panel.getBrickBtn().isSelected()){
            panel.setStyle(ImageStore.readImage("model/images/blockmodel/brick.jpg", panel.getPreferredSize(), panel.getPreferredSize()));
        }
        if (panel.getTreeBtn().isSelected()){
            panel.setStyle(ImageStore.readImage("model/images/blockmodel/tree.png", panel.getPreferredSize(), panel.getPreferredSize()));
        }

        if (panel.getMarioBtn().isSelected()){
            panel.setCharacter(ImageStore.readImage("model/images/charactermodel/mario.png", panel.getPreferredSize()+scale, panel.getPreferredSize()+scale));
        }
        if (panel.getKnightBtn().isSelected()){
            panel.setCharacter(ImageStore.readImage("model/images/charactermodel/knight.png", panel.getPreferredSize()+scale, panel.getPreferredSize()+scale));
        }
        if (panel.getZombieBtn().isSelected()){
            panel.setCharacter(ImageStore.readImage("model/images/charactermodel/zombie.png", panel.getPreferredSize()+scale, panel.getPreferredSize()+scale));

        }

    }
    
}