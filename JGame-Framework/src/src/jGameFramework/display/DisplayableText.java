package jGameFramework.display;

import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import java.awt.*;

/**
 * A text is a String which can be show on the frame
 *
 * @author Jérémie Beaudoin-Dion
 */
public class DisplayableText extends Displayable {
	
	private String message;
	private Font font;
	private Paint paint;

	/**
	 * Displayable text for specific position
	 */
	public DisplayableText(Position position, DisplayableDepth depth, String message, Font font, Paint paint){
		super(position, new Position(font.getSize()/2 * message.length(), font.getSize()), depth);
		this.message = message;
		this.font = font;
		this.paint = paint;
	}

    /**
     * Displayable text from base object
     */
    public DisplayableText(PhysicalObject baseObject, Position cameraPosition, String message, Font font, Paint paint){
        super(baseObject.getPositionAccordingToCamera(cameraPosition),
                new Position(font.getSize()/2 * message.length(), font.getSize()),
                baseObject.getDepth());
        this.message = message;
        this.font = font;
        this.paint = paint;
    }

	public String getMessage() {
		return message;
	}

	public Font getFont() {
		return font;
	}
	
	public Paint getPaint() {
		return paint;
	}

}

