package addGameObjectsHere;

import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableShapeFilled;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class ButtonRoll extends PhysicalObject{

    private Font font;

    /**
     * Constructor
     */
    public ButtonRoll() {
        super(getStartBoundingArea(), false);

        font = new Font("Century Schoolbook", Font.PLAIN, 25);
    }

    private static BoundingArea getStartBoundingArea() {

        Position position = BoundingArea.getCenterPosition(new Rectangle2D.Double(0, 0, GameInformation.WINDOW_WIDTH, GameInformation.WINDOW_HEIGHT));

        int width = GameInformation.WINDOW_WIDTH/6;
        int height = GameInformation.WINDOW_HEIGHT/6;

        return new BoundingArea(new RoundRectangle2D.Double(position.getX() - width/2, position.getY() - height/2,
                width, height, 20, 20));
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

        images.add(new DisplayableShapeFilled(900, getBoundingArea(), new Color(50, 100, 100)));

        images.add(new DisplayableText(getBoundingArea().getCenterPosition().add(new Position(-getWidth()/4, 0)),
                1000, "ROLL!", font, Color.WHITE));

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
