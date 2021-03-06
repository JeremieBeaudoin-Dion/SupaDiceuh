package helper;

import jGameFramework.core.LoaderOfImages;
import jGameFramework.physicalObjects.PlaneDimension;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * An ensemble of images stored in a Sprite.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class SpriteImage<T> extends LoaderOfImages {

    private HashMap<T, BufferedImage> allImages;

    public SpriteImage(String path, HashMap<T, PlaneDimension> allImageIndex) throws IOException {
        BufferedImage sourceImage = toCompatibleImage(ImageIO.read(new File(path)));

        allImages = new HashMap<>();

        for(Map.Entry<T, PlaneDimension> entry : allImageIndex.entrySet()) {
            PlaneDimension dimensions = entry.getValue();

            allImages.put(entry.getKey(), sourceImage.getSubimage(dimensions.getX(), dimensions.getY(), dimensions.getWidth(), dimensions.getHeight()));
        }
    }

    public Set<T> getAllKeys() {
        return allImages.keySet();
    }

    public BufferedImage get(T key) {
        return allImages.get(key);
    }
}
