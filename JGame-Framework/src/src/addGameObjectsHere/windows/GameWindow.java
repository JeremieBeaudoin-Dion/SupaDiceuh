package addGameObjectsHere.windows;

import helper.BoxCreator;
import addResourceLoaderHere.GameInformation;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.*;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * This represents the menus of the game which are made to be moved around and resized.
 *
 * When clicking on their WindowButton, the player can move them around or expand/retract the window.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class GameWindow extends PhysicalObjectUpdating implements MouseInteractingPhysicalObject {

    public static final int BORDER_HEIGHT = 20;
    public static final int MAX_FRAME_BUTTON_COUNTER_VALUE = 30;

    private static final int MINIMUM_WIDTH = GameInformation.WINDOW_WIDTH/3;
    private static final int MINIMUM_HEIGHT = GameInformation.WINDOW_HEIGHT/2;

    private Position expandedWidthAndHeight;

    private WindowButton windowButton;
    private boolean showDetails;
    private WindowID windowID;

    private WindowsCounter counter;

    private static final Font TITLE_FONT =  new Font("Century Schoolbook", Font.BOLD, 35);

    /**
     * Constructor
     */
    public GameWindow(WindowID windowID, Position position) {
        super(new BoundingArea(position.getX(), position.getY(), MINIMUM_WIDTH, MINIMUM_HEIGHT), false,
                new VisionRectangle(MINIMUM_WIDTH, MINIMUM_HEIGHT), WindowDepthManager.getDepth());

        windowButton = new WindowButton(windowID, getPosition(),getDepth().add(50));
        expandedWidthAndHeight = new Position(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        setAllBasicAttributes();
    }

    /**
     * Constructor
     */
    public GameWindow(WindowID windowID, BoundingArea boundingArea) {
        super(boundingArea, false,
                new VisionRectangle((int) boundingArea.getWidth(), (int) boundingArea.getHeight()),
                WindowDepthManager.getDepth());

        windowButton = new WindowButton(windowID, getPosition(), getDepth().add(50));
        expandedWidthAndHeight = new Position(boundingArea.getWidth(), boundingArea.getHeight());
        setAllBasicAttributes();
    }

    private void setAllBasicAttributes() {
        showDetails = true;
        this.windowID = windowID;

        counter = new WindowsCounter(MAX_FRAME_BUTTON_COUNTER_VALUE);
    }

    /**
     * Method called every frame
     */
    @Override
    public TreeSet<PhysicalObject> update(TreeSet<PhysicalObject> surroundings) {
        windowButton.update(surroundings);

        if (!windowButton.getPosition().equals(this.getPosition())) {
            this.setPositionTo(windowButton.getPosition());
        }

        counter.update();

        return doUpdate(surroundings);
    }

    protected abstract TreeSet<PhysicalObject> doUpdate(TreeSet<PhysicalObject> surroundings);

    /**
     * Adds necessary images
     */
    public abstract TreeSet<Displayable> getAdditionalImages(Position cameraPosition, ImageLoader imageLoader);

    /**
     * Returns the image representation of this physical object
     */
    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {

        TreeSet<Displayable> images = new TreeSet<>();

        images.addAll(windowButton.getImageObjects(cameraPosition, imageLoader));

        if (isShowDetails()) {

            images.add(new DisplayableImage(this, cameraPosition,
                    imageLoader.getBox(new Position(getWidth(), getHeight()), BoxCreator.Background.Paper)));

            images.add(new DisplayableText(getPositionOfTitle(cameraPosition), getDepth().add(10),
                    getWindowTitle(), TITLE_FONT, Color.BLACK));

            images.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition), getDepth().add(20),
                    imageLoader.getBox(new Position(getWidth(), BORDER_HEIGHT), BoxCreator.Background.Wood)));

            images.addAll(getAdditionalImages(cameraPosition, imageLoader));
        }

        return images;
    }

    protected String getWindowTitle() {
        return windowID.toString();
    }

    private Position getPositionOfTitle(Position cameraPosition) {

        Position posAccordingToCamera = getPositionAccordingToCamera(cameraPosition);

        Position posPlusWindow = posAccordingToCamera.add(new Position(windowButton.getBoundingArea().getWidth(), 0));

        return posPlusWindow.add(new Position(10, 70));
    }

    @Override
    public List<GameEvent> getAction() {
        return null;
    }

    @Override
    public boolean dispose() {
        return false;
    }

    protected Position getImagesOffset() {
        return new Position(windowButton.getBoundingArea().getWidth()/7, windowButton.getBoundingArea().getHeight());
    }

    protected BoundingArea getDetailsArea() {
        return new BoundingArea(getPosition().getX() + getImagesOffset().getX(),
                getPosition().getY() + getImagesOffset().getY(),
                (int) getWidth() - getImagesOffset().getX(),
                (int) getHeight() - getImagesOffset().getY());
    }

    /*
     * Mouse interacting methods
     */
    @Override
    public void doLeftMousePressed(Position mousePositionCollidingWithObject) {
        if (windowButton.isColliding(mousePositionCollidingWithObject)) {
            windowButton.doLeftMousePressed(mousePositionCollidingWithObject);

            counter.start();
        } else {
            if (isShowDetails() && getDetailsArea().collidesWith(mousePositionCollidingWithObject)) {
                doLeftMousePressedOnDetails(mousePositionCollidingWithObject);
            }
        }
    }

    protected abstract void doLeftMousePressedOnDetails(Position mousePositionCollidingWithObject);

    @Override
    public void doLeftMouseReleased(Position mousePositionCollidingWithObject) {
        if (windowButton.isColliding(mousePositionCollidingWithObject)) {
            windowButton.doLeftMouseReleased(mousePositionCollidingWithObject);

            showOrHideDetails();
            counter.stop();
        } else {
            if (showDetails && getDetailsArea().collidesWith(mousePositionCollidingWithObject)) {
                doLeftMouseReleasedOnDetails(mousePositionCollidingWithObject);
            }
        }
    }

    protected abstract void doLeftMouseReleasedOnDetails(Position mousePositionCollidingWithObject);

    private void showOrHideDetails() {
        if (!counter.isDone()) {
            showDetails = !showDetails;

            if (isShowDetails()) {
                expand();
            } else {
                retract();
            }
        }
    }

    private void expand() {
        setWidthAndHeight(expandedWidthAndHeight.getX(), expandedWidthAndHeight.getY());
    }

    private void retract() {
        setWidthAndHeight(windowButton.getBoundingArea().getWidth(), windowButton.getBoundingArea().getHeight());
    }

    @Override
    protected void resizeWidthAndHeight(double widthMultiplier, double heightMultiplier) {
        super.resizeWidthAndHeight(widthMultiplier, heightMultiplier);
        expandedWidthAndHeight = new Position(expandedWidthAndHeight.getX() * widthMultiplier,
                                                expandedWidthAndHeight.getY() * heightMultiplier);
    }

    @Override
    public void doRightMousePressed(Position mousePositionCollidingWithObject) {

    }

    @Override
    public void doRightMouseReleased(Position mousePositionCollidingWithObject) {

    }

    /**
     * Getter
     */
    protected boolean isShowDetails() {
        return showDetails;
    }
}
