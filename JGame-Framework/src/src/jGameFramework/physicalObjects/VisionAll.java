package jGameFramework.physicalObjects;

import java.io.Serializable;

/**
 * A type of Vision that sees all.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class VisionAll implements Vision, Serializable {
    @Override
    public boolean isInSight(Position centerOfOwner, PhysicalObject other) {
        return true;
    }
}
