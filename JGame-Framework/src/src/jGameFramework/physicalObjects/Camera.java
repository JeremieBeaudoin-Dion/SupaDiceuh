package jGameFramework.physicalObjects;

import jGameFramework.core.Game;
import jGameFramework.display.DisplayableDepth;

import java.awt.geom.Rectangle2D;

/**
 * A camera has a position, and the Width and Height of the Game
 *
 * It defines what will be shown in ImageHandler
 *
 * This can be modified but not removed, it can be ignored by object,
 * but the ObjectHandler will always assume that a Camera object exists.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class Camera extends PhysicalObjectMoving {

    /**
     * Constructor
     */
    public Camera(Velocity velocity) {
        super(new BoundingArea(new Rectangle2D.Double(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT)),
                false, new DisplayableDepth(DisplayableDepth.BACKGROUND),
                new VisionRectangle(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT), velocity);
    }

    /**
     * Returns true if the PhysicalObject collides with the CameraWithEdges
     *
     * If a zoom is implemented, a new method will be needed
     */
    public boolean isShownByCamera(PhysicalObject physicalObject) {
        return isColliding(physicalObject);
    }

    /**
     * Resizing does not change the camera's position, only its width and height
     */
    @Override
    public void resize(Position lastScreenSize, Position newScreenSize) {
        double widthMultiplier = newScreenSize.getX() / (double) lastScreenSize.getX();
        double heightMultiplier = newScreenSize.getY() / (double) lastScreenSize.getY();

        resizeWidthAndHeight(widthMultiplier, heightMultiplier);
    }

}
