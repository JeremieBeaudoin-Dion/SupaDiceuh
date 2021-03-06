package jGameFramework.display;

/**
 * Objects and images in this framework are ordered by depth.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class DisplayableDepth implements Comparable<DisplayableDepth> {

    public static final int BACKGROUND = 0;
    public static final int MIDDLE = Integer.MAX_VALUE/2;
    public static final int HIGHEST = Integer.MAX_VALUE;

    private int value;

    /**
     * Creates a Depth. The bigger the value, the closer to
     * the screen the Displayable will be.
     *
     * The minimum value BACKGROUND is zero.
     *
     * @param value: integer bigger than zero
     */
    public DisplayableDepth(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("The value must be bigger than zero. Value: " + value);
        }

        this.value = value;
    }

    public DisplayableDepth add(int valueToAdd) {
        return new DisplayableDepth(value + valueToAdd);
    }

    @Override
    public int compareTo(DisplayableDepth other) {
        return this.value - other.value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DisplayableDepth)) {
            return false;
        }

        return this.value == ((DisplayableDepth) other).value;
    }

}
