package addGameObjectsHere.windows;

import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A Layout of physical object which will organize them in a BoundingArea.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class PhysicalObjectLayout<X extends PhysicalObject> extends PhysicalObject {

    private Set<X> physicalObjects;

    /**
     * Constructor
     */
    public PhysicalObjectLayout(Set<X> objects, BoundingArea boundingArea, DisplayableDepth depth) {
        super(boundingArea, false, depth);

        physicalObjects = objects;
        setObjectsPositions();
    }

    private void setObjectsPositions() {
        int x = getPosition().getX();
        int y = getPosition().getY();

        for (PhysicalObject physicalObject: physicalObjects) {
            physicalObject.setPositionTo(new Position(x, y));
            y += physicalObject.getBoundingArea().getHeight();
        }
    }

    @Override
    public void setPositionTo(Position newPosition) {
        Position differenceOfPosition = getPosition().add(newPosition.reverse());

        moveBy(differenceOfPosition);
    }

    public void moveBy(Position differenceOfPosition) {
        for (PhysicalObject physicalObject: physicalObjects) {
            physicalObject.setPositionTo(physicalObject.getPosition().add(differenceOfPosition.reverse()));
        }

        super.setPositionTo(getPosition().add(differenceOfPosition.reverse()));
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        for (PhysicalObject physicalObject : physicalObjects) {
            if (isWithinBounds(physicalObject)) {
                images.addAll(physicalObject.getImageObjects(cameraPosition, imageLoader));
            }
        }

        return images;
    }

    /**
     * Returns any action that the GameThread should handle
     */
    @Override
    public List<GameEvent> getAction() {
        List<GameEvent> actions = new LinkedList<>();

        for (PhysicalObject physicalObject : physicalObjects) {
            actions.addAll(physicalObject.getAction());
        }

        return actions;
    }

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        physicalObjects.removeIf(PhysicalObject::dispose);

        return false;
    }

    /**
     * Returns true if the object should be disposed of
     */
    public Set<X> getPhysicalObjects() {
        return physicalObjects;
    }

    /**
     * PhysicalObjectLayout will not handle the logic behind clicking on a PhysicalObject. If the mouse is colliding
     * with an object, it will be returned.
     */
    public X doMouseClick(Position mousePosition) {
        for (X physicalObject: physicalObjects) {
            if (physicalObject.isColliding(mousePosition)) {
                return physicalObject;
            }
        }

        return null;
    }

    public void setPhysicalObjects(Set<X> physicalObjects) {
        this.physicalObjects = physicalObjects;
        setObjectsPositions();
    }

    public void addPhysicalObject(X physicalObject) {
        this.physicalObjects.add(physicalObject);
        setObjectsPositions();
    }

    public void removeObject(X physicalObject) {
        physicalObjects.remove(physicalObject);
        setObjectsPositions();
    }
}
