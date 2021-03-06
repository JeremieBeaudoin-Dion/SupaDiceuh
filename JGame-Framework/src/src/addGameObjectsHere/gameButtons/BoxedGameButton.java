package addGameObjectsHere.gameButtons;

import helper.BoxCreator;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.coreActions.GameEvent;
import jGameFramework.display.Displayable;
import jGameFramework.display.DisplayableDepth;
import jGameFramework.display.DisplayableImage;
import jGameFramework.display.DisplayableText;
import jGameFramework.physicalObjects.BoundingArea;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Jérémie Beaudoin-Dion
 */
public class BoxedGameButton extends GameButton {

    private Font font = new Font("Century Schoolbook", Font.PLAIN, 30);
    private Color color = new Color(220, 160, 70);
    private String name;

    public BoxedGameButton(ButtonID buttonID, Position startingPosition,
                           List<GameEvent> eventActions, DisplayableDepth depth) {
        super(buttonID, startingPosition, eventActions, depth);

        this.name = buttonID.toString();
    }

    public BoxedGameButton(ButtonID buttonID, BoundingArea startBoundingArea,
                           List<GameEvent> eventActions, DisplayableDepth depth) {
        super(buttonID, startBoundingArea, eventActions, depth);

        this.name = buttonID.toString();
    }

    /**
     * Constructor for specific name (which is different from the button id
     */
    public BoxedGameButton(ButtonID buttonID, BoundingArea startBoundingArea,
                           List<GameEvent> eventActions, DisplayableDepth depth, String name) {
        super(buttonID, startBoundingArea, eventActions, depth);

        this.name = name;
    }

    public BoxedGameButton(ButtonID buttonID, Position startingPosition,
                           List<GameEvent> eventActions, DisplayableDepth depth, String name) {
        super(buttonID, startingPosition, eventActions, depth);

        this.name = name;
    }

    @Override
    public TreeSet<Displayable> getImageObjects(Position cameraPosition, ImageLoader imageLoader) {
        TreeSet<Displayable> images = new TreeSet<>();

        images.add(new DisplayableImage(getPositionAccordingToCamera(cameraPosition), getDepth(),
                imageLoader.getBox(new Position(getWidth(), getHeight()), BoxCreator.Background.Wood)));

        Position positionOfText = getPositionAccordingToCamera(cameraPosition);
        positionOfText = positionOfText.add(new Position(5, font.getSize()));

        images.add(new DisplayableText(positionOfText, getDepth(), name, font, color));

        return images;
    }
}
