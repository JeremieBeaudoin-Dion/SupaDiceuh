package addGameObjectsHere;

import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class Results extends PhysicalObject {

    private int currentroll;

    private Random generator;

    private Font font;

    /**
     * Constructor
     */
    public Results() {
        super(getStartBoundingArea(), false, new DisplayableDepth(600));

        this.currentroll = 0;
        generator = new Random();
        font = new Font("Century Schoolbook", Font.PLAIN, 25);
    }

    private static BoundingArea getStartBoundingArea() {
        return new BoundingArea(new Rectangle2D.Double(620, 270, 100, 150));
    }

    void doroll(int numberofdices) {

        int number = 0;

        for (int i=0; i<numberofdices; i++) {
            number += rolldice();
        }

        currentroll = number;
    }

    private int rolldice() {
        return generator.nextInt(3);
    }

    /**
     * Returns the image representation of this physical object
     * <p>
     * The ImageObject will have a relative position depending
     * on the position of the CameraWithEdges.
     *
     * @param cameraPosition
     * @param imageLoader
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableText(getPosition(), new DisplayableDepth(150), "Result", font, Color.WHITE));

        images.add(new DisplayableText(getBoundingArea().getCenterPosition().add((new Position(GameInformation.WINDOW_WIDTH/30, 0)).reverse()),
                new DisplayableDepth(320), String.valueOf(currentroll), font, Color.WHITE));

        return images;
    }

    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }

}
