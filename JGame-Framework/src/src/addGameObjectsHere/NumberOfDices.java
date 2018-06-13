package addGameObjectsHere;

import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.MouseInteractingPhysicalObject;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class NumberOfDices extends PhysicalObject implements MouseInteractingPhysicalObject {

    private int currentnumberofdices;

    private Font font;

    private ButtonMoreDice buttonMoreDice;

    private ButtonLessDice buttonLessDice;

    /**
     * Constructor
     */
    public NumberOfDices() {
        super(getStartBoundingArea(), false);

        this.currentnumberofdices = 1;

        font = new Font("Century Schoolbook", Font.PLAIN, 25);

        Position baselineposition = getBoundingArea().getCenterPosition().add((new Position(GameInformation.WINDOW_WIDTH/30, 0)).reverse());

        buttonMoreDice = new ButtonMoreDice(baselineposition.add(new Position(50, -25)));
        buttonLessDice = new ButtonLessDice(baselineposition.add(new Position(-50, -25)));
    }

    private static BoundingArea getStartBoundingArea() {
        return new BoundingArea(new Rectangle2D.Double(120, 270, 200, 150));
    }

    void addOne() {
        if (currentnumberofdices < 99) {
            currentnumberofdices++;
        }
    }

    void removeOne() {
        if (currentnumberofdices > 1) {
            currentnumberofdices--;
        }
    }

    void setToMax() {
        currentnumberofdices = 99;
    }

    void setToZero() {
        currentnumberofdices = 1;
    }

    int getCurrentnumberofdices() {
        return currentnumberofdices;
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

        images.add(new DisplayableText(getPosition(),200, "Number of Dice", font, Color.WHITE));

        images.add(new DisplayableText(getBoundingArea().getCenterPosition().add((new Position(GameInformation.WINDOW_WIDTH/30, 0)).reverse()),
                300, String.valueOf(currentnumberofdices), font, Color.WHITE));

        images.addAll(buttonMoreDice.getImageObjects(cameraPosition, imageLoader));
        images.addAll(buttonLessDice.getImageObjects(cameraPosition, imageLoader));

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

        buttonMoreDice.resize(lastScreenSize, newScreenSize);
        buttonLessDice.resize(lastScreenSize, newScreenSize);
    }

    /*
     * Mouse interactions
     */

    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        if (buttonLessDice.isColliding(mousePositionCollidingWithObject)) {
            removeOne();
        }
        if (buttonMoreDice.isColliding(mousePositionCollidingWithObject)) {
            addOne();
        }
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {
        if (buttonLessDice.isColliding(mousePositionCollidingWithObject)) {
            setToZero();
        }
        if (buttonMoreDice.isColliding(mousePositionCollidingWithObject)) {
            setToMax();
        }
    }
}
