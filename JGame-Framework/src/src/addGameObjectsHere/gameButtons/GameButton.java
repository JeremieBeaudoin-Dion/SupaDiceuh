package addGameObjectsHere.gameButtons;

import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.MouseInteractingPhysicalObject;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.util.List;

/**
 * Game buttons will always have the same behaviour:
 *      When clicked, they will change the current GameThread
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class GameButton extends PhysicalObject implements MouseInteractingPhysicalObject {

    public static final int DEFAULT_WIDTH = 100;
    public static final int DEFAULT_HEIGHT = 50;

    private boolean isPressed;
    private ButtonID buttonID;

    private List<GameEvent> eventActions;

    /**
     * Constructor
     *
     * If the bounding area is not specified, the default bounding area will be used.
     */
    public GameButton(ButtonID buttonID, Position startingPosition, List<GameEvent> eventActions, DisplayableDepth depth) {
        super(getStartingBoundingArea(startingPosition), false, depth);

        isPressed = false;
        this.buttonID = buttonID;
        this.eventActions = eventActions;
    }

    private static BoundingArea getStartingBoundingArea(Position startingPosition) {
        return new BoundingArea(startingPosition.getX(), startingPosition.getY(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Constructor with specific BoundingArea
     */
    public GameButton(ButtonID buttonID, BoundingArea startBoundingArea, List<GameEvent> eventActions, DisplayableDepth depth) {
        super(startBoundingArea, false, depth);

        isPressed = false;
        this.buttonID = buttonID;
        this.eventActions = eventActions;
    }

    @Override
    public List<GameEvent> getAction() {

        if (isPressed) {
            isPressed = false;

            return eventActions;
        }

        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }

    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        isPressed = true;
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }

    /*
     * Getters
     */
    public boolean getIsPressed() {
        return isPressed;
    }

    public ButtonID getButtonID() {
        return buttonID;
    }
}
