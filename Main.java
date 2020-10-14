import javax.swing.JFrame;

import view.MenuScreen;

public class Main {
    
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(400, 100);

        var menu = new MenuScreen(window);
        menu.init();

        window.pack();
        window.setVisible(true);
    }
}