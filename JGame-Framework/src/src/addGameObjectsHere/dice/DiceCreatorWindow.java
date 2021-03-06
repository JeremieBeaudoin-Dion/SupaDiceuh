package addGameObjectsHere.dice;

import addGameObjectsHere.gameButtons.ButtonID;
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
public class DiceCreatorWindow extends GameWindowForm {

    private boolean createNewDiceWindow;

    /**
     * Constructor
     */
    public DiceCreatorWindow() {
        super(WindowID.Dice, getButtons(), new BoundingArea(50, 50, 300, 150), 3);
        createNewDiceWindow = false;
    }

    private static List<ButtonID> getButtons() {
        List<ButtonID> buttonList = new LinkedList<>();

        buttonList.add(ButtonID.New);

        return buttonList;
    }

    @Override
    protected String getWindowTitle() {
        return "Dice";
    }

    @Override
    protected TreeSet<PhysicalObject> doUpdate(TreeSet<PhysicalObject> surroundings) {
        if (!createNewDiceWindow) {
            return null;
        }

        TreeSet<PhysicalObject> newObjects = new TreeSet<>();

        newObjects.add(new DiceRollerWindow(getPosition().add(new Position(50, 50))));
        createNewDiceWindow = false;

        return newObjects;
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
        if (buttonID == ButtonID.New) {
            createNewDiceWindow = true;
        }
    }
}
