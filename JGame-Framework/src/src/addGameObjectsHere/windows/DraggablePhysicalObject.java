package addGameObjectsHere.windows;

import addResourceLoaderHere.GameInformation;
import jGameFramework.core.MouseHandler;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.*;

import java.util.TreeSet;

/**
 * A type of object that can be dragged with the mouse.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class DraggablePhysicalObject extends PhysicalObjectUpdating implements MouseInteractingPhysicalObject {

    private boolean isMoving;
    private Position offset;

    /**
     * Constructor
     */
    public DraggablePhysicalObject(BoundingArea area, boolean isObstacle, Vision vision, DisplayableDepth baseDepth) {
        super(area, isObstacle, vision, baseDepth);

        isMoving = false;
    }

    private void startMoving(Position mousePositionCollidingWithObject) {
        isMoving = true;
        offset = mousePositionCollidingWithObject.add(getPosition().reverse());
        hasStartedMoving();
    }

    private void stopMoving() {
        isMoving = false;
        hasStoppedMoving();
    }

    protected abstract void hasStoppedMoving();

    protected abstract void hasStartedMoving();

    protected abstract void doUpdate(TreeSet<PhysicalObject> surroundings);

    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {
        if (!isMoving) {
            startMoving(mousePositionCollidingWithObject);
        } else {
            stopMoving();
        }
    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        stopMoving();
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

        if (isMoving) {
            if (newPositionIsOutsideBoundaries()) {
                stopMoving();

            } else {
                setPositionTo(getNewPosition());
            }
        }

        doUpdate(surroundings);

        return new TreeSet<>();
    }

    private Position getNewPosition() {
        return MouseHandler.getMousePositionRelativeToScreen().add(offset.reverse());
    }

    private boolean newPositionIsOutsideBoundaries() {
        Position newPosition = getNewPosition();

        if (newPosition.getX() < 0) {
            return true;
        }

        if (newPosition.getY() < 0) {
            return true;
        }

        if (newPosition.getX() > GameInformation.WINDOW_WIDTH - getWidth()) {
            return true;
        }

        if (newPosition.getY() > GameInformation.WINDOW_HEIGHT - getHeight()) {
            return true;
        }

        return false;
    }
}
