package jGameFramework.physicalObjects;

import java.io.Serializable;

/**
 * A velocity determines how much a PhysicalObject
 * should move in a certain direction according to
 * its speed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public abstract class Velocity implements Serializable {

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    /**
     * Returns the Position to add to go the the desired direction
     *
     * @param deltaValue : This is a time-relative event, so it needs its deltaValue
     *                   to get the precise velocity according to time.
     */
    Position goTo(Direction direction, double deltaValue){
        switch (direction){
            case UP:
                return goUp(deltaValue);

            case DOWN:
                return goDown(deltaValue);

            case LEFT:
                return goLeft(deltaValue);

            case RIGHT:
                return goRight(deltaValue);
        }

        throw new IllegalArgumentException("The direction: " + direction + " does not exists.");
    }

    abstract Position goUp(double deltaValue);

    abstract Position goDown(double deltaValue);

    abstract Position goLeft(double deltaValue);

    abstract Position goRight(double deltaValue);

}
