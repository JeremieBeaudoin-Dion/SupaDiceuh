package jGameFramework.coreActions;

import jGameFramework.core.threadObjects.GameThreadHandler;
import jGameFramework.exceptions.ActionInvocationException;

import java.lang.reflect.Method;

/**
 * A specific GameEvent that will play a specific Music according to
 * its id.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameEventPlayMusic extends GameThreadEvent {

    public GameEventPlayMusic(String musicID) {
        super(getPlayMusicMethod(), musicID);
    }

    private static Method getPlayMusicMethod(){
        try {
            return GameThreadHandler.class.getMethod("playMusic", String.class);
        } catch (NoSuchMethodException e) {
            throw new ActionInvocationException("playMusic", GameThreadHandler.class, e.getMessage());
        }
    }
}
