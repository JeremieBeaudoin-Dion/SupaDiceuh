package addResourceLoaderHere;

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


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return new InputActionKeyMap(keydownactionmap, null, mouseDownActionMap, mouseReleaseActionMap, null);
    }

}

