package addResourceLoaderHere;

import addGameObjectsHere.DiceRoller;
import jGameFramework.collections.InputActionKeyMap;
import jGameFramework.core.Loader;
import jGameFramework.core.MouseHandler;
import jGameFramework.core.threadObjects.ObjectHandler;
import jGameFramework.coreActions.*;
import jGameFramework.physicalObjects.Position;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * An instance of Loader that returns InputActionKeyMap,
 * a Map necessary to interpret input from the user.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ActionLoader implements Loader<InputActionKeyMap> {

    public InputActionKeyMap get(GameThreadID gameThreadID){
        return getStartGameKeyMap();
    }

    private InputActionKeyMap getStartGameKeyMap(){
        HashMap<MouseHandler.MouseClick, Action> mouseReleaseActionMap = new HashMap<>();
        HashMap<MouseHandler.MouseClick, Action> mouseDownActionMap = new HashMap<>();
        HashMap<Integer, Action> keydownactionmap = new HashMap<>();

        try {
            GameEvent<ObjectHandler> leftClickEvent = new GameEvent<>(ObjectHandler.class, ObjectHandler.class.getMethod("doLeftMouseReleased", Position.class));
            ActionTimed leftClickAction = new ActionTimed(leftClickEvent, 100);

            mouseReleaseActionMap.put(MouseHandler.MouseClick.Left, leftClickAction);

            GameEvent<ObjectHandler> rightClickEvent = new GameEvent<>(ObjectHandler.class, ObjectHandler.class.getMethod("doRightMouseReleased", Position.class));
            ActionTimed rightClickAction = new ActionTimed(rightClickEvent, 100);

            mouseReleaseActionMap.put(MouseHandler.MouseClick.Right, rightClickAction);

            GameEvent<ObjectHandler> leftClickEvent2 = new GameEvent<>(ObjectHandler.class, ObjectHandler.class.getMethod("doLeftMousePressed", Position.class));
            ActionTimed leftClickAction2 = new ActionTimed(leftClickEvent2, 100);

            mouseDownActionMap.put(MouseHandler.MouseClick.Left, leftClickAction2);

            GameEvent<ObjectHandler> rightClickEvent2 = new GameEvent<>(ObjectHandler.class, ObjectHandler.class.getMethod("doRightMousePressed", Position.class));
            ActionTimed rightClickAction2 = new ActionTimed(rightClickEvent2, 100);

            mouseDownActionMap.put(MouseHandler.MouseClick.Right, rightClickAction2);

            GameEvent<DiceRoller> keyevent1 = new GameEvent<>(DiceRoller.class, DiceRoller.class.getMethod("dokeyinput1"));
            Action keyaction1 = new Action(keyevent1);
            keydownactionmap.put(KeyEvent.VK_1, keyaction1);

            GameEvent<DiceRoller> keyevent2 = new GameEvent<>(DiceRoller.class, DiceRoller.class.getMethod("dokeyinput2"));
            Action keyaction2 = new Action(keyevent2);
            keydownactionmap.put(KeyEvent.VK_2, keyaction2);

            GameEvent<DiceRoller> keyevent3 = new GameEvent<>(DiceRoller.class, DiceRoller.class.getMethod("dokeyinput3"));
            Action keyaction3 = new Action(keyevent3);
            keydownactionmap.put(KeyEvent.VK_3, keyaction3);

            GameEvent<DiceRoller> keyevent4 = new GameEvent<>(DiceRoller.class, DiceRoller.class.getMethod("dokeyinput4"));
            Action keyaction4 = new Action(keyevent4);
            keydownactionmap.put(KeyEvent.VK_4, keyaction4);

            GameEvent<DiceRoller> keyevent5 = new GameEvent<>(DiceRoller.class, DiceRoller.class.getMethod("dokeyinput5"));
            Action keyaction5 = new Action(keyevent5);
            keydownactionmap.put(KeyEvent.VK_5, keyaction5);

            GameEvent<DiceRoller> keyevent6 = new GameEvent<>(DiceRoller.class, DiceRoller.class.getMethod("dokeyinput6"));
            Action keyaction6 = new Action(keyevent6);
            keydownactionmap.put(KeyEvent.VK_6, keyaction6);

            GameEvent<DiceRoller> keyevent7 = new GameEvent<>(DiceRoller.class, DiceRoller.class.getMethod("dokeyinput7"));
            Action keyaction7 = new Action(keyevent7);
            keydownactionmap.put(KeyEvent.VK_7, keyaction7);

            GameEvent<DiceRoller> keyevent8 = new GameEvent<>(DiceRoller.class, DiceRoller.class.getMethod("dokeyinput8"));
            Action keyaction8 = new Action(keyevent8);
            keydownactionmap.put(KeyEvent.VK_8, keyaction8);

            GameEvent<DiceRoller> keyevent9 = new GameEvent<>(DiceRoller.class, DiceRoller.class.getMethod("dokeyinput9"));
            Action keyaction9 = new Action(keyevent9);
            keydownactionmap.put(KeyEvent.VK_9, keyaction9);


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new InputActionKeyMap(keydownactionmap, null, mouseDownActionMap, mouseReleaseActionMap);
    }

}

