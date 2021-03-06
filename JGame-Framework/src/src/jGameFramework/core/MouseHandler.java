package jGameFramework.core;

import jGameFramework.core.threadObjects.GameThreadHandler;
import jGameFramework.physicalObjects.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * The Listener of the Mouse for the Game.
 *
 * Sends the actions to the InputHandler.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class MouseHandler implements java.awt.event.MouseListener {

    public enum MouseClick {Right, Left}

    private Map<Integer, MouseClick> integerToClickMap;

    private GameThreadHandler gameThreadHandler;

    MouseHandler(GameThreadHandler gameThreadHandler){
        this.gameThreadHandler = gameThreadHandler;

        integerToClickMap = new HashMap<>();
        integerToClickMap.put(MouseEvent.BUTTON3, MouseClick.Right);
        integerToClickMap.put(MouseEvent.BUTTON1, MouseClick.Left);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        gameThreadHandler.getCurrentInputHandler().mousePressed(integerToClickMap.get(mouseEvent.getButton()),
                getMousePositionRelativeToScreen(new Position(mouseEvent.getX(), mouseEvent.getY())));
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        gameThreadHandler.getCurrentInputHandler().mouseReleased(integerToClickMap.get(mouseEvent.getButton()),
                getMousePositionRelativeToScreen(new Position(mouseEvent.getX(), mouseEvent.getY())));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static Position getMousePositionRelativeToScreen(Position mouseEventPosition){
        return mouseEventPosition.add(new Position(-ImageHandler.INSETS.left, -ImageHandler.INSETS.top));
    }

    public static Position getMousePositionRelativeToScreen() {
        Position mousePosition = new Position(MouseInfo.getPointerInfo().getLocation().getX(),
                MouseInfo.getPointerInfo().getLocation().getY());

        return mousePosition.add(new Position(-ImageHandler.INSETS.left -ImageHandler.POSITION_ON_SCREEN.getX(),
                -ImageHandler.INSETS.top -ImageHandler.POSITION_ON_SCREEN.getY()));
    }
}
