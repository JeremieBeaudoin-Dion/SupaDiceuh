package addGameObjectsHere;

import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.TreeSet;

/**
 * The main title of the game
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Title extends PhysicalObjectUpdating implements MouseInteractingPhysicalObject {

    private static final Font FONT_TITLE = new Font("Century Schoolbook", Font.PLAIN, 50);

    private ButtonMoreDice buttonMore;

    /**
     * Constructor
     */
    public Title() {
        super(getStartBoundingArea(), false, new VisionRectangle(0, 0), new DisplayableDepth(50));

        //buttonMore = new ButtonMoreDice(new Position(GameInformation.WINDOW_WIDTH/2.3, 30));
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

        images.add(new DisplayableText(getPosition(), getDepth(), "Supah Diceuh", FONT_TITLE, Color.WHITE));

        //images.addAll(buttonMore.getImageObjects(cameraPosition, imageLoader));

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

    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {
        if (buttonMore.isColliding(mousePositionCollidingWithObject)) {
            //create new window
        }
    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }

    /**
     * Method called every frame
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        return null;
    }
}
