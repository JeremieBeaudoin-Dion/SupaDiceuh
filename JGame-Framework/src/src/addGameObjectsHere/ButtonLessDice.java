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
public class ButtonLessDice extends PhysicalObject {

    private Color currentColor;

    private static Color redcolor = new Color(125, 0, 25);
    private static Color redercolor = new Color(175, 0, 50);

    /**
     * Constructor
     */
    public ButtonLessDice(Position position) {
        super(getStartBoundingArea(position), false);

        currentColor = redcolor;
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

        images.add(new DisplayableShapeFilled(700, getBoundingArea(), currentColor));

        images.add(new DisplayableShapeFilled(800,
                new Rectangle2D.Double(getPosition().getX(), getBoundingArea().getCenterPosition().getY() - 3, getWidth(), 5), Color.WHITE));

        return images;
    }

    void doMouseRelease() {
        currentColor = redcolor;
    }

    void doMousePressed() {
        currentColor = redercolor;
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
