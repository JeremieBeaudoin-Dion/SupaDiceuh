package jGameFramework.core.threadObjects;

import addResourceLoaderHere.GameThreadID;
import addResourceLoaderHere.ImageLoader;
import jGameFramework.collections.InputActionKeyMap;
import jGameFramework.display.Displayable;
import jGameFramework.physicalObjects.Camera;
import jGameFramework.physicalObjects.PhysicalObject;
import jGameFramework.physicalObjects.Position;

import javax.sound.sampled.AudioInputStream;
import java.util.Map;
import java.util.TreeSet;

/**
 * A Game can have multiple threads which consists of an ActionHandler,
 * an ObjectHandler, an InputHandler
 *
 * @author Jérémie Beaudoin-Dion
 */
public class GameThread {

    private GameThreadID id;

    private ObjectHandler objectHandler;
    private ActionHandler actionHandler;
    private InputHandler inputHandler;
    private MusicHandler musicHandler;
    private SoundHandler soundHandler;

    GameThread(GameThreadHandler gameThreadHandler, GameThreadID gameThreadID, Camera camera,
               TreeSet<PhysicalObject> allGameObjects, InputActionKeyMap inputActionKeyMap,
               Map<String, AudioInputStream> musicMap, Map<String, AudioInputStream> soundMap){

        this.objectHandler = new ObjectHandler(allGameObjects, camera);
        this.actionHandler = new ActionHandler(gameThreadHandler, objectHandler);
        this.inputHandler = new InputHandler(inputActionKeyMap, actionHandler);
        this.musicHandler = new MusicHandler(musicMap);
        this.soundHandler = new SoundHandler(soundMap);

        this.id = gameThreadID;
    }

    public void update(double deltaValue) {
        actionHandler.update();
        objectHandler.update(deltaValue);
    }

    GameThreadSerialized getSerial(){
        return new GameThreadSerialized(id, objectHandler.getAllObjectsForSave(), objectHandler.getCamera());
    }

    TreeSet<Displayable> getImages(ImageLoader imageLoader) {
        return objectHandler.get(imageLoader);
    }

    void resize(Position lastSize, Position newScreenSize) {
        objectHandler.resize(lastSize, newScreenSize);
    }

    InputHandler getInputHandler() {
        return inputHandler;
    }

    void playSound(String id){
        soundHandler.play(id);
    }

    void playMusic(String id){
        musicHandler.play(id);
    }

    void stopMusic(String id){
        musicHandler.stop(id);
    }

    /**
     * Ensures that there is no memory leak.
     *
     * This is mostly important for the SoundHandler and MusicHandler
     * with clips that should be closed.
     */
    void clear(){
        objectHandler = null;
        actionHandler = null;
        inputHandler = null;

        musicHandler.closeAll();
        musicHandler = null;

        soundHandler.closeAll();
        soundHandler = null;
    }

}
