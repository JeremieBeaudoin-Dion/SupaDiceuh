package jGameFramework.coreActions;

/**
 * An action which takes account of the time
 * between each of the calls.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ActionTimed extends Action {

    private long timeOfLastCall;
    private long timeBetweenEachEvents;

    /**
     * Constructor
     *
     * @param gameEvent: the action that will be returned if isDoable()
     * @param timeBetweenEachEvents: in milliseconds
     */
    public ActionTimed(GameEvent gameEvent, long timeBetweenEachEvents){
        super(gameEvent);

        timeOfLastCall = System.currentTimeMillis();
        this.timeBetweenEachEvents = timeBetweenEachEvents;
    }

    @Override
    public boolean isDoable() {
        return System.currentTimeMillis() - timeOfLastCall >= timeBetweenEachEvents;
    }

    @Override
    public GameEvent getAction(){
        timeOfLastCall = System.currentTimeMillis();

        return gameEvent;
    }

}
