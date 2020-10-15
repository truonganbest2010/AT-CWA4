package model.images;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageStore {
    
    public static BufferedImage block;

    public static BufferedImage character;

    public static BufferedImage door;
    public static BufferedImage exit;



    static {
        // String cwd = System.getProperty("user.dir");
        // System.out.println("cwd" + cwd + "\n");
        block = readImage("model/images/blockmodel/block.jpg", 15, 15);

        door = readImage("model/images/blockmodel/door.jpg", 15, 15);
        exit = readImage("model/images/blockmodel/exit.jpg", 38, 31);


        character = readImage("model/images/charactermodel/character.png", 15, 15);
    }

    public static BufferedImage readImage(String path, int width, int height){
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));
            Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImage.createGraphics();
            g2.drawImage(tmp, 0, 0, null);
            g2.dispose();
            return resizedImage;
        } catch (Exception e){
            System.out.println("Image file load error");
        }
        return null;
    }

}