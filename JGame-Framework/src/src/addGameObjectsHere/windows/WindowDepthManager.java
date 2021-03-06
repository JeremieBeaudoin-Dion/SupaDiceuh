package addGameObjectsHere.windows;

import jGameFramework.display.DisplayableDepth;

/**
 * A class that helps manage game windows' depth.
 *
 * @author Jérémie Beaudoin-Dion
 */
class WindowDepthManager {

    private static int lastDepth = 500;

    static DisplayableDepth getDepth() {
        if (lastDepth + 100 > Integer.MAX_VALUE) {
            lastDepth = 500;
        }

        lastDepth += 100;

        return new DisplayableDepth(lastDepth);
    }

}
