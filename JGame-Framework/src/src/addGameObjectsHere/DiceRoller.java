package addGameObjectsHere;

import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableShapeFilled;
import jGameFramework.physicalObjects.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class DiceRoller extends PhysicalObjectUpdating implements MouseInteractingPhysicalObject {

    private static Color backgroundcolor = new Color(20, 50, 0, 90);

    private Results results;

    private NumberOfDices numberOfDices;

    private ButtonRoll buttonRoll;

    private int nextkeyvalue;

    /**
     * Constructor
     */
    public DiceRoller() {
        super(getStartBoundingArea(), false, new VisionRectangle(50, 50));

        results = new Results();

        numberOfDices = new NumberOfDices();

        buttonRoll = new ButtonRoll();

        nextkeyvalue = 0;
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
        if (numberOfDices.isColliding(mousePositionCollidingWithObject)) {
            numberOfDices.doLeftMousePressed(mousePositionCollidingWithObject);
        }
        if (buttonRoll.isColliding(mousePositionCollidingWithObject)) {
            buttonRoll.doMousePressed();
        }
    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        if (numberOfDices.isColliding(mousePositionCollidingWithObject)) {
            numberOfDices.doLeftMouseReleased(mousePositionCollidingWithObject);
        }
        if (buttonRoll.isColliding(mousePositionCollidingWithObject)) {
            results.doroll(numberOfDices.getCurrentnumberofdices());
        }

        buttonRoll.doMouseRelease();
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {
        if (numberOfDices.isColliding(mousePositionCollidingWithObject)) {
            numberOfDices.doRightMousePressed(mousePositionCollidingWithObject);
        }
    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {
        if (numberOfDices.isColliding(mousePositionCollidingWithObject)) {
            numberOfDices.doRightMouseReleased(mousePositionCollidingWithObject);
        }
    }

    public void dokeyinput1() {
        nextkeyvalue = 1;
    }

    public void dokeyinput2() {
        nextkeyvalue = 2;
    }

    public void dokeyinput3() {
        nextkeyvalue = 3;
    }

    public void dokeyinput4() {
        nextkeyvalue = 4;
    }

    public void dokeyinput5() {
        nextkeyvalue = 5;
    }

    public void dokeyinput6() {
        nextkeyvalue = 6;
    }

    public void dokeyinput7() {
        nextkeyvalue = 7;
    }

    public void dokeyinput8() {
        nextkeyvalue = 8;
    }

    public void dokeyinput9() {
        nextkeyvalue = 9;
    }

    /**
     * Method called every frame
     *
     * @param surroundings
     * @return any new PhysicalObject created by this one
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {

        if (nextkeyvalue != 0) {
            numberOfDices.setdice(nextkeyvalue);
            nextkeyvalue = 0;
        }

        return null;
    }
}
