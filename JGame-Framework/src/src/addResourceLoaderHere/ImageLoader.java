package addResourceLoaderHere;

import addGameObjectsHere.windows.WindowID;
import helper.BoxCreator;
import jGameFramework.core.LoaderOfImages;
import jGameFramework.physicalObjects.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

/**
 * Loads images in order to be displayed on screen
 *
 * This class is necessary for the JGame Framework to work.
 * It can load as many images as needed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ImageLoader extends LoaderOfImages {

    private EnumMap<WindowID, BufferedImage> windowIdImages;

    private BoxCreator boxCreator;

    /**
     * Basic constructor
     * @throws IOException if an image is missing
     */
    public ImageLoader() throws IOException {

        windowIdImages = new EnumMap<>(WindowID.class);

        boxCreator = new BoxCreator();

        loadAllImages();
    }

    /**
     * Loads all image from file
     *
     * Do remember to use toCompatibleImage() which greatly increases the performance
     * of jGameFramework.display.
     *
     * @throws IOException : if the image is missing
     */
    private void loadAllImages() throws IOException {

        windowIdImages.put(WindowID.Quests, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/map.png"))));
        windowIdImages.put(WindowID.Adventurers, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/backpack.png"))));
        windowIdImages.put(WindowID.Recruit, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/backpack.png"))));
        windowIdImages.put(WindowID.Shop, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/coin.png"))));
        windowIdImages.put(WindowID.Inn, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/inn.png"))));
        windowIdImages.put(WindowID.Dice, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/Icons/dice.png"))));


    }

    public BufferedImage getWindowImage(WindowID id) {
        return windowIdImages.get(id);
    }

    public BufferedImage getBox(Position dimensions, BoxCreator.Background background) {
        return boxCreator.getBox(dimensions, background);
    }

}
