package jGameFramework.display;

import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;

/**
 * A displayable shape which will be filled with a desired color.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class DisplayableShapeFilled extends DisplayableShape {

    /**
     * Constructors with specific depth and shape
     */
    public DisplayableShapeFilled(DisplayableDepth depth, Shape shape, Paint paint) {
        super(depth, shape, paint);
    }

    /**
     * Constructors for basic PhysicalObject
     */
    public DisplayableShapeFilled(PhysicalObject baseObject, Position cameraPosition, Paint paint) {
        super(baseObject.getDepth(), baseObject.getAreaAccordingToCamera(cameraPosition), paint);
    }

}
