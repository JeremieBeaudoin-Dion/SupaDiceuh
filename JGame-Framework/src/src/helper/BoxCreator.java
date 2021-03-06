package helper;

import addResourceLoaderHere.GameInformation;
import jGameFramework.core.LoaderOfImages;
import jGameFramework.physicalObjects.PlaneDimension;
import jGameFramework.physicalObjects.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * This will return a customizable Box image when calling getBox()
 *
 * The code can still be cleaner.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class BoxCreator extends LoaderOfImages {

    public enum Background{Paper, Wood}

    private enum SelectorLineDirections{Top, Down, Left, Right;
        int getIsX() {

            if (this == Top || this == Down) {
                return 1;
            }

            return 0;
        }

        int getIsY() {
            if (this == Left || this == Right) {
                return 1;
            }

            return 0;
        }
    }

    private enum SelectorCornerDirection {

        TopLeft, TopRight, BottomLeft, BottomRight;

        int getIsRight() {
            if (this == TopLeft || this == BottomLeft) {
                return 1;
            }

            return 0;
        }

        int getIsBottom() {
            if (this == BottomLeft || this == BottomRight) {
                return 1;
            }
            return 0;
        }
    }

    private static final int SELECTOR_CORNER_WIDTH_AND_HEIGHT = 8;
    private static final int SELECTOR_WIDTH = 1280;
    private static final int SELECTOR_HEIGHT = 720;

    private HashMap<Background, BufferedImage> background_images;
    private SpriteImage<String> selector;

    /**
     * Constructor
     */
    public BoxCreator() throws IOException {

        background_images = new HashMap<>();

        background_images.put(Background.Paper, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/PaperBackground.png"))));
        background_images.put(Background.Wood, toCompatibleImage(ImageIO.read(new File("Resources/Buttons/WoodBackground.png"))));

        selector = new SpriteImage<>("Resources/Buttons/Selector.png", getAllSelectorImageDimensions());
    }

    private HashMap<String, PlaneDimension> getAllSelectorImageDimensions() {
        HashMap<String, PlaneDimension> allImageIndex = new HashMap<>();

        for (SelectorCornerDirection direction : SelectorCornerDirection.values()) {

            Position position = new Position((SELECTOR_WIDTH - SELECTOR_CORNER_WIDTH_AND_HEIGHT) * direction.getIsRight(),
                    (SELECTOR_HEIGHT - SELECTOR_CORNER_WIDTH_AND_HEIGHT) * direction.getIsBottom());
            Position widthAndHeight = new Position(SELECTOR_CORNER_WIDTH_AND_HEIGHT, SELECTOR_CORNER_WIDTH_AND_HEIGHT);

            allImageIndex.put(direction.toString(),
                    new PlaneDimension(position, widthAndHeight));
        }

        allImageIndex.put(SelectorLineDirections.Top.toString(), new PlaneDimension(
                new Position(SELECTOR_CORNER_WIDTH_AND_HEIGHT, 0),
                new Position(SELECTOR_WIDTH - SELECTOR_CORNER_WIDTH_AND_HEIGHT*2, SELECTOR_CORNER_WIDTH_AND_HEIGHT)));

        allImageIndex.put(SelectorLineDirections.Down.toString(), new PlaneDimension(
                new Position(SELECTOR_CORNER_WIDTH_AND_HEIGHT, SELECTOR_HEIGHT - SELECTOR_CORNER_WIDTH_AND_HEIGHT),
                new Position(SELECTOR_WIDTH - SELECTOR_CORNER_WIDTH_AND_HEIGHT*2, SELECTOR_CORNER_WIDTH_AND_HEIGHT)));

        allImageIndex.put(SelectorLineDirections.Left.toString(), new PlaneDimension(
                new Position(0, SELECTOR_CORNER_WIDTH_AND_HEIGHT),
                new Position(SELECTOR_CORNER_WIDTH_AND_HEIGHT, SELECTOR_HEIGHT - SELECTOR_CORNER_WIDTH_AND_HEIGHT*2)));

        allImageIndex.put(SelectorLineDirections.Right.toString(), new PlaneDimension(
                new Position(SELECTOR_WIDTH - SELECTOR_CORNER_WIDTH_AND_HEIGHT, SELECTOR_CORNER_WIDTH_AND_HEIGHT),
                new Position(SELECTOR_CORNER_WIDTH_AND_HEIGHT, SELECTOR_HEIGHT - SELECTOR_CORNER_WIDTH_AND_HEIGHT*2)));


        return allImageIndex;
    }

    /**
     * Returns a box of a certain type of background
     */
    public BufferedImage getBox(Position dimensions, Background backgroundID) {

        BufferedImage box = new BufferedImage(dimensions.getX(), dimensions.getY(), BufferedImage.TYPE_INT_RGB);
        Graphics2D bbg = (Graphics2D) box.getGraphics();

        drawBackground(dimensions, bbg, backgroundID);

        drawSelector(dimensions, bbg);

        bbg.dispose();

        return box;
    }

    private void drawBackground(Position dimensions, Graphics2D bbg, Background backgroundID) {
        bbg.drawImage(getBackground(dimensions, backgroundID), 0, 0, null);
    }

    private BufferedImage getBackground(Position dimensions, Background backgroundID) {
        return background_images.get(backgroundID).getSubimage(0, 0, dimensions.getX(), dimensions.getY());
    }

    private void drawSelector(Position dimensions, Graphics2D bbg) {
        drawAllSelectorCorners(dimensions, bbg);
        drawAllSelectorLines(dimensions, bbg);
    }

    private void drawAllSelectorCorners(Position dimensions, Graphics2D bbg) {
        for (SelectorCornerDirection direction : SelectorCornerDirection.values()) {
            bbg.drawImage(selector.get(direction.toString()),
                    (dimensions.getX() - SELECTOR_CORNER_WIDTH_AND_HEIGHT) * direction.getIsRight(),
                    (dimensions.getY() - SELECTOR_CORNER_WIDTH_AND_HEIGHT) * direction.getIsBottom(), null);
        }
    }

    private void drawAllSelectorLines(Position dimensions, Graphics2D bbg) {

        Position dimensionOfLines = dimensions.add(
                new Position(-(SELECTOR_CORNER_WIDTH_AND_HEIGHT * 2), -(SELECTOR_CORNER_WIDTH_AND_HEIGHT * 2)));


        bbg.drawImage(
                getSelectorLine(SelectorLineDirections.Top, new Position(dimensionOfLines.getX(), SELECTOR_CORNER_WIDTH_AND_HEIGHT)),
                SELECTOR_CORNER_WIDTH_AND_HEIGHT, 0, null);

        bbg.drawImage(
                getSelectorLine(SelectorLineDirections.Down, new Position(dimensionOfLines.getX(), SELECTOR_CORNER_WIDTH_AND_HEIGHT)),
                SELECTOR_CORNER_WIDTH_AND_HEIGHT, dimensions.getY() - SELECTOR_CORNER_WIDTH_AND_HEIGHT, null);

        bbg.drawImage(
                getSelectorLine(SelectorLineDirections.Left, new Position(SELECTOR_CORNER_WIDTH_AND_HEIGHT, dimensionOfLines.getY())),
                0, SELECTOR_CORNER_WIDTH_AND_HEIGHT, null);

        bbg.drawImage(
                getSelectorLine(SelectorLineDirections.Right, new Position(SELECTOR_CORNER_WIDTH_AND_HEIGHT, dimensionOfLines.getY())),
                dimensions.getX() - SELECTOR_CORNER_WIDTH_AND_HEIGHT, SELECTOR_CORNER_WIDTH_AND_HEIGHT, null);
    }

    /**
     * Returns a selector line of the specified length
     */
    private BufferedImage getSelectorLine(SelectorLineDirections direction, Position widthAndHeight) {
        return selector.get(direction.toString()).getSubimage(0,0, widthAndHeight.getX(), widthAndHeight.getY());
    }

}
