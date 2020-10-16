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

        int scale = 5;

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
        if (panel.getFenceBtn().isSelected()){
            panel.setStyle(ImageStore.readImage("model/images/blockmodel/fence.png", panel.getPreferredSize(), panel.getPreferredSize()));
        }
        if (panel.getIronBtn().isSelected()){
            panel.setStyle(ImageStore.readImage("model/images/blockmodel/iron.png", panel.getPreferredSize(), panel.getPreferredSize()));
        }

        if (panel.getMarioBtn().isSelected()){
            panel.setCharacter(ImageStore.readImage("model/images/charactermodel/mario.png", panel.getPreferredSize()+scale, panel.getPreferredSize()+scale));
        }
        if (panel.getCharmanderBtn().isSelected()){
            panel.setCharacter(ImageStore.readImage("model/images/charactermodel/charmander.png", panel.getPreferredSize()+scale, panel.getPreferredSize()+scale));
        }
        if (panel.getMagnetoBtn().isSelected()){
            panel.setCharacter(ImageStore.readImage("model/images/charactermodel/magneto.png", panel.getPreferredSize()+scale, panel.getPreferredSize()+scale));
        }

        if (panel.getDrawLineCheck().isSelected()){
            panel.setLine(true);
        }

    }
    
}