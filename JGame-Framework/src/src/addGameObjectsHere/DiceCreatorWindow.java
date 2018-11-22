package addGameObjectsHere;

import addGameObjectsHere.windows.GameWindow;
import addGameObjectsHere.windows.WindowID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class DiceCreatorWindow extends GameWindow {

    /**
     * Constructor
     */
    public DiceCreatorWindow() {
        super(WindowID.Dice, new Position(50, 50));
    }

    @Override
    protected TreeSet<PhysicalObject> doUpdate(TreeSet<PhysicalObject> surroundings) {
        return null;
    }

    @Override
    public TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader) {
        return new TreeSet<>();
    }

    @Override
    protected void doLeftMousePressedOnDetails(Position mousePositionCollidingWithObject) {

    }

    @Override
    protected void doLeftMouseReleasedOnDetails(Position mousePositionCollidingWithObject) {

    }
}
