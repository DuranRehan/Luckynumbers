package g56055.luckynumbers.model;

import java.util.Objects;

/**
 *
 * @author Duran Rehan g56055, define the characteristics of a tile in the game
 * Lucky Numbers
 */
public class Tile {

    private final Integer value;

    /**
     * Constructor for Tile class, define a tile with a value
     *
     * @param value
     */
    public Tile(Integer value) {
        this.value = value;
    }

    /**
     * Get the value of value
     *
     * @return the value of value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Representation of the object in String
     *
     * @return value attribute in String
     */
    @Override
    public String toString() {
        return "{" + value + "}";
    }

    /**
     * Checks if the value of the tile is equal to the value of another tile
     * using a hashCode
     *
     * @return a certain hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /**
     * Checks if the value of the tile is equal to the value of another tile
     *
     * @param obj
     * @return true if the tiles are equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tile other = (Tile) obj;
        return Objects.equals(this.value, other.value);
    }
}
