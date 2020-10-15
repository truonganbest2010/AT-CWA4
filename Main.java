import javax.swing.JFrame;

import view.ConfigScreen;

public class Main {
    
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(200, 100);

        var menu = new ConfigScreen(window);
        menu.init();

        window.pack();
        window.setVisible(true);
    }
}