package addGameObjectsHere;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableShapeFilled;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class ButtonMoreDice extends PhysicalObject {

    private static Color greencolor = new Color(50, 100, 0);

    /**
     * Constructor
     */
    public ButtonMoreDice(Position position) {
        super(getStartBoundingArea(position), false);
    }

    private static BoundingArea getStartBoundingArea(Position position) {
        return new BoundingArea(new Rectangle2D.Double(position.getX(), position.getY(), 30, 30));
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

        images.add(new DisplayableShapeFilled(400, getBoundingArea(), greencolor));

        images.add(new DisplayableShapeFilled(500,
                new Rectangle2D.Double(getPosition().getX(), getBoundingArea().getCenterPosition().getY() - 3, getWidth(), 5), Color.WHITE));

        images.add(new DisplayableShapeFilled(600,
                new Rectangle2D.Double(getBoundingArea().getCenterPosition().getX() - 3, getPosition().getY(), 5, getHeight()), Color.WHITE));

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
