package worldofzuul.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Rasmus Willer
 */
public class ImageLoading {
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageLoading.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
