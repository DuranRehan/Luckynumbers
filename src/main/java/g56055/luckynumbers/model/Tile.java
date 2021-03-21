package g56055.luckynumbers.model;

import java.util.Objects;

/**
 *
 * @author Duran Rehan g56055
 *
 * Define the characteristics of a tile in the game Lucky Numbers
 */
public class Tile {

    private final int value;

    /**
     * Define a tile with a specific value
     *
     * @param value value that will be placed on the tile
     */
    public Tile(Integer value) {
        this.value = value;
    }

    /**
     * Get the value of a tile
     *
     * @return the value of tile
     */
    public Integer getValue() {
        return value;
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
     * @param obj the tile to compare with the actual tile
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
