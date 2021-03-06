package jGameFramework.core;

/**
 * An ObjectWithId will take account of all the objects that were
 * created and sort them in order of appearance.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ObjectWithID implements Comparable<ObjectWithID> {

    private static ObjectIdCounter objectIdCounter;

    private int id;

    protected ObjectWithID(){
        if (objectIdCounter == null){
            objectIdCounter = new ObjectIdCounter();
        }

        id = objectIdCounter.next();
    }

    public static int countAllInstances(){
        return objectIdCounter.size();
    }

    protected int getId(){
        return id;
    }

    @Override
    protected void finalize() throws Throwable {
        objectIdCounter.remove(id);

        super.finalize();
    }


    @Override
    public int compareTo(ObjectWithID objectWithID) {
        return id - objectWithID.id;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ObjectWithID)) {
            return false;
        }

        return this.id == ((ObjectWithID) other).id;
    }
}
