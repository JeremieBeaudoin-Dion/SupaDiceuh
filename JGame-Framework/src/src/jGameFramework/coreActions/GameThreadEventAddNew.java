package jGameFramework.coreActions;

import addResourceLoaderHere.GameThreadID;
import jGameFramework.core.threadObjects.GameThreadHandler;
import jGameFramework.exceptions.ActionInvocationException;

import java.lang.reflect.Method;

/**
 * A specific GameEvent that will create a new Thread to the game.
 *
 * This Thread will be added on top of the current stack of Threads.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameThreadEventAddNew extends GameThreadEvent {

    public GameThreadEventAddNew(GameThreadID newThreadValue) {
        super(getNewGameStateMethod(), newThreadValue);
    }

    private static Method getNewGameStateMethod(){
        try {
            return GameThreadHandler.class.getMethod("addNewGameState", GameThreadID.class);
        } catch (NoSuchMethodException e) {
            throw new ActionInvocationException("addNewGameState", GameThreadHandler.class, e.getMessage());
        }
    }


}
