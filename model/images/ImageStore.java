package model.images;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageStore {
    
    public static BufferedImage brick;

    public static BufferedImage door;



    static {
        // String cwd = System.getProperty("user.dir");
        // System.out.println("cwd" + cwd + "\n");
        brick = readImage("model/images/blockmodel/brick.jpg", 15, 15);

        door = readImage("model/images/blockmodel/door.jpg", 15, 15);
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