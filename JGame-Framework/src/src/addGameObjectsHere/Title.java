package addGameObjectsHere;

import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.TreeSet;

/**
 * The main title of the game
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Title extends PhysicalObject {

    private static final Font FONT_TITLE = new Font("Century Schoolbook", Font.PLAIN, 50);

    /**
     * Constructor
     */
    public Title() {
        super(getStartBoundingArea(), false);
    }

    public static BoundingArea getStartBoundingArea() {
        return new BoundingArea(new Rectangle2D.Float(30, 60, GameInformation.WINDOW_WIDTH*2/3,
                GameInformation.WINDOW_HEIGHT*2/3));
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

        images.add(new DisplayableText(getPosition(), 50, "Supah Diceuh", FONT_TITLE, Color.WHITE));

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
