package addGameObjectsHere;

import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableShapeFilled;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.MouseInteractingPhysicalObject;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class DiceRoller extends PhysicalObject implements MouseInteractingPhysicalObject {

    private static Color backgroundcolor = new Color(20, 50, 0, 90);

    private Results results;

    private NumberOfDices numberOfDices;

    private ButtonRoll buttonRoll;

    /**
     * Constructor
     */
    public DiceRoller() {
        super(getStartBoundingArea(), false);

        results = new Results();

        numberOfDices = new NumberOfDices();

        buttonRoll = new ButtonRoll();
    }

    private static BoundingArea getStartBoundingArea() {
        return new BoundingArea(new RoundRectangle2D.Double(60, 150, GameInformation.WINDOW_WIDTH/1.2,
                GameInformation.WINDOW_HEIGHT/1.6, 5, 5));
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

        images.add(new DisplayableShapeFilled(10, getBoundingArea(), backgroundcolor));

        images.addAll(results.getImageObjects(cameraPosition, imageLoader));

        images.addAll(numberOfDices.getImageObjects(cameraPosition, imageLoader));

        images.addAll(buttonRoll.getImageObjects(cameraPosition, imageLoader));

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
    public void resize(Position lastScreenSize, Position newScreenSize) {
        super.resize(lastScreenSize, newScreenSize);

        results.resize(lastScreenSize, newScreenSize);

        numberOfDices.resize(lastScreenSize, newScreenSize);

        buttonRoll.resize(lastScreenSize, newScreenSize);
    }

    /*
     * Mouse interaction with this object
     */
    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        if (numberOfDices.isColliding(mousePositionCollidingWithObject)) {
            numberOfDices.doLeftMouseReleased(mousePositionCollidingWithObject);
        }
        if (buttonRoll.isColliding(mousePositionCollidingWithObject)) {
            results.doroll(numberOfDices.getCurrentnumberofdices());
        }
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {
        if (numberOfDices.isColliding(mousePositionCollidingWithObject)) {
            numberOfDices.doRightMouseReleased(mousePositionCollidingWithObject);
        }
    }
}
