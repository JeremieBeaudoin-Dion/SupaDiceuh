package jGameFramework.physicalObjects;

/**
 * A type of PhysicalObject which interacts with the mouse wheel.
 *
 * @author Jérémie Beaudoin-Dion
 */
public interface MouseWheelInteractingPhysicalObject {

    void doWheelUp(Position mousePosition, int amount);

    void doWheelDown(Position mousePosition, int amount);

}
