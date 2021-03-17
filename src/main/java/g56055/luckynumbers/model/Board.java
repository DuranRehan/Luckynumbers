package g56055.luckynumbers.model;

import java.util.Arrays;

/**
 *
 * @author Duran Rehan g56055
 *
 * Define the game board on which the player will play
 */
public class Board {

    private Tile[][] tiles;

    /**
     * define a game board with a given size by the getSize method
     */
    public Board() {
        int size = getSize();
        this.tiles = new Tile[size][size];
    }

    /**
     * Get the size of the game board
     *
     * @return the size of game board
     */
    public int getSize() {
        return 4;
    }

    /**
     * Check if the given position is inside the game board
     *
     * @param pos position to check
     * @return true if the position is inside the board false otherwise
     */
    public boolean isInside(Position pos) {
        boolean testRow = pos.getRow() <= getSize() - 1
                && pos.getRow() >= 0;
        boolean testColumn = pos.getColumn() <= getSize() - 1
                && pos.getColumn() >= 0;
        return testRow && testColumn;
    }

    /**
     * Get tile of a given position
     *
     * @param pos position of the tile on the game board
     * @return the tile at position
     */
    public Tile getTile(Position pos) {
        return tiles[pos.getRow()][pos.getColumn()];
    }

    /**
     * Checks if the given tile can be placed in the given position while
     * respecting the rules.
     *
     * @param tile tile for which it is necessary to check if it can be placed
     * @param pos position of the tile to check
     * @return true if the tile can be placed, false otherwise
     */
    public boolean canBePut(Tile tile, Position pos) {
        int lg = pos.getRow();
        while (lg > 0 && pos.getColumn() > 0) {
            if (tiles[lg][pos.getColumn()].getValue() > tile.getValue()) {
                return false;
            }
            lg--;
        }
        return true;
    }

    /**
     * Place on the game board a given tile at a specific position
     *
     * @param tile the tile to place
     * @param pos the position where the tile should be placed
     */
    public void put(Tile tile, Position pos) {
        tiles[pos.getRow()][pos.getColumn()] = tile;
    }

    /**
     * checks if the board is completely filled with tiles.
     *
     * @return true if the board is full, false otherwise
     */
    public boolean isFull() {
        for (Tile[] lg : tiles) {
            for (Tile col : lg) {
                if (col == null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(tiles);
        //TO COMPLETE AND MODIFYE
    }

}
