package addResourceLoaderHere;

import addGameObjectsHere.camera.CameraStill;
import addGameObjectsHere.dice.DiceCreatorWindow;
import jGameFramework.core.Loader;
import jGameFramework.physicalObjects.Camera;
import jGameFramework.physicalObjects.PhysicalObject;

import java.util.TreeSet;

/**
 * Loads all game objects to create GameThreads.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class PhysicalObjectLoader implements Loader<TreeSet<PhysicalObject>> {

    private ImageLoader imageLoader;

    public PhysicalObjectLoader(ImageLoader imageLoader){
        this.imageLoader = imageLoader;
    }

    public TreeSet<PhysicalObject> get(GameThreadID gameThreadID){
        TreeSet<PhysicalObject> objects = new TreeSet<>();

        objects.add(new DiceCreatorWindow());

        return objects;
    }

    public Camera getCamera(GameThreadID gameThreadID){
        return new CameraStill();
    }

}
