package jGameFramework.display;

import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;

/**
 * A shape that can be put on screen. Only its outline will be
 * seen. To show a FilledShape, use DisplayableShapeFilled.
 *
 * The default stroke value is a solid outline.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class DisplayableShapeOutline extends DisplayableShape {

    private Stroke stroke;

    /**
     * Constructors for a solid BLACK outline
     */
    public DisplayableShapeOutline(DisplayableDepth depth, Shape shape) {
        super(depth, shape, Color.BLACK);
        this.stroke = new BasicStroke();
    }

    /**
     * Constructors for a solid outline
     */
    public DisplayableShapeOutline(DisplayableDepth depth, Shape shape, Paint paint) {
        super(depth, shape, paint);
        this.stroke = new BasicStroke();
    }

    /**
     * Constructor for basic object with solid outline
     */
    public DisplayableShapeOutline(PhysicalObject baseObject, Position cameraPosition, Paint paint) {
        super(baseObject.getDepth(), baseObject.getAreaAccordingToCamera(cameraPosition), paint);
        this.stroke = new BasicStroke();
    }

    /**
     * Constructor for a specific desired outline
     */
    public DisplayableShapeOutline(DisplayableDepth depth, Shape shape, Paint paint, Stroke stroke){
        super(depth, shape, paint);
        this.stroke = stroke;
    }

    /**
     * Constructor for a specific desired outline for basic object
     */
    public DisplayableShapeOutline(PhysicalObject baseObject, Position cameraPosition, Paint paint, Stroke stroke){
        super(baseObject.getDepth(), baseObject.getAreaAccordingToCamera(cameraPosition), paint);
        this.stroke = stroke;
    }

    public Stroke getStroke(){
        return stroke;
    }

}
