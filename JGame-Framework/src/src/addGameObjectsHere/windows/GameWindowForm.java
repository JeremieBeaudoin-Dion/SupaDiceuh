package addGameObjectsHere.windows;

import addGameObjectsHere.gameButtons.BoxedGameButton;
import addGameObjectsHere.gameButtons.ButtonID;
import addGameObjectsHere.gameButtons.GameButton;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * A type of GameWindow which has buttons in it with actions.
 *
 * The forms will be disposed of when a day/night cycle is called.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class GameWindowForm extends GameWindow {

    private List<GameButton> buttons;
    private List<ButtonID> buttonIDs;
    private boolean dispose;
    private int buttonDivider;

    /**
     * Constructor
     */
    public GameWindowForm(WindowID windowID, List<ButtonID> buttonIDs, Position position) {
        super(windowID, position);

        buttonDivider = 6;
        setAllBasicAttributes(buttonIDs);
    }

    public GameWindowForm(WindowID windowID, List<ButtonID> buttonIDs, BoundingArea area) {
        super(windowID, area);

        buttonDivider = 6;
        setAllBasicAttributes(buttonIDs);
    }

    public GameWindowForm(WindowID windowID, List<ButtonID> buttonIDs, BoundingArea area, int buttonDivider) {
        super(windowID, area);

        this.buttonDivider = buttonDivider;
        setAllBasicAttributes(buttonIDs);
    }

    private void setAllBasicAttributes(List<ButtonID> buttonIDs) {
        this.dispose = false;
        this.buttonIDs = buttonIDs;

        resetButtons();
    }

    private void resetButtons() {
        buttons = new LinkedList<>();

        int numberOfButtons = buttonIDs.size();

        int width = (int) (getWidth()/numberOfButtons);
        int height = getButtonHeight();
        int x = getPosition().getX();
        int y = getPosition().getY() + (int) getHeight() - height;

        for (ButtonID buttonID: buttonIDs) {
            buttons.add(new BoxedGameButton(buttonID, new BoundingArea(x, y, width, height), null, getDepth().add(1)));
            x += width;
        }
    }

    private int getButtonHeight() {
        return (int) getHeight()/buttonDivider;
    }

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> images = new TreeSet<>();

        if (isShowDetails()) {
            for (GameButton button: buttons) {
                images.addAll(button.getImageObjects(cameraPosition, imageLoader));
            }
        }

        images.addAll(super.getImageObjects(cameraPosition, imageLoader));

        return images;
    }

    /**
     * Returns true if the object should be disposed of
     */
    @Override
    public boolean dispose() {
        return dispose;
    }

    protected void setDispose(boolean dispose) {
        this.dispose = dispose;
    }

    @Override
    public void setPositionTo(Position newPosition) {

        super.setPositionTo(newPosition);

        if (isShowDetails()) {
            resetButtons();
        }
    }

    @Override
    protected void setWidthAndHeight(double width, double height) {
        super.setWidthAndHeight(width, height);
        if (isShowDetails())
            resetButtons();
    }

    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {
        if (isShowDetails()) {
            for (GameButton button: buttons) {
                if (button.isColliding(mousePositionCollidingWithObject)) {
                    button.doLeftMousePressed(mousePositionCollidingWithObject);
                    return;
                }
            }
        }

        super.doLeftMousePressed(mousePositionCollidingWithObject);
    }

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {

        if (isShowDetails()) {
            for (GameButton button: buttons) {
                if (button.isColliding(mousePositionCollidingWithObject)) {
                    button.doLeftMouseReleased(mousePositionCollidingWithObject);
                    doButtonReleased(button.getButtonID());
                    return;
                }
            }
        }

        super.doLeftMouseReleased(mousePositionCollidingWithObject);
    }

    protected abstract void doButtonReleased(ButtonID buttonID);

    @Override
    protected BoundingArea getDetailsArea() {
        return new BoundingArea(getImagesOffset().getX(), getImagesOffset().getY(),
                (int) getWidth() - getImagesOffset().getX(),
                (int) getHeight() - getImagesOffset().getY() - getButtonHeight());
    }
}
