package addGameObjectsHere.dice;

import addGameObjectsHere.gameButtons.ButtonID;
import addGameObjectsHere.windows.GameWindow;
import addGameObjectsHere.windows.GameWindowForm;
import addGameObjectsHere.windows.WindowID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class DiceRollerWindow extends GameWindowForm {

    private boolean dispose;

    /**
     * Constructor
     */
    DiceRollerWindow(BoundingArea area) {
        super(WindowID.Dice, allButtons(), area);

        dispose = false;
    }

    DiceRollerWindow(Position position) {
        super(WindowID.Dice, allButtons(), position);

        dispose = false;
    }

    private static List<ButtonID> allButtons(){
        List<ButtonID> buttonList = new LinkedList<>();

        buttonList.add(ButtonID.Delete);

        return buttonList;
    }

    @Override
    protected String getWindowTitle() {
        return "Roll";
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

    @Override
    protected void doButtonReleased(ButtonID buttonID) {
        dispose = true;
    }

    @Override
    public boolean dispose() {
        return dispose;
    }
}
