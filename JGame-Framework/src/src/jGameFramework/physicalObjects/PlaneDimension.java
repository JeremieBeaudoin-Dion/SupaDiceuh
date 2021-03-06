package jGameFramework.physicalObjects;

/**
 * Represents the position, width and height of an image.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class PlaneDimension {

    private Position position;
    private Position dimensions;

    public PlaneDimension(Position position, Position dimensions) {
        this.position = position;
        this.dimensions = dimensions;
    }

    public Position getPosition() {
        return position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public int getWidth() {
        return dimensions.getX();
    }

    public int getHeight() {
        return dimensions.getY();
    }
}
