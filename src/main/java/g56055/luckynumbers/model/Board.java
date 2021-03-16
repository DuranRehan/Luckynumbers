package g56055.luckynumbers.model;

import java.util.Arrays;

/**
 *
 * @author Duran Rehan g56055, Define the game board on which the player will
 * play
 */
public class Board {

    private Tile[][] tiles;

    /**
     * Constructor for Board class, define a game board with a size given by the
     * getSize method
     */
    public Board() {
        int size = getSize();
        tiles = new Tile[size][size];
        for (Tile[] lg : tiles) {
            for (Tile col : lg) {
                col = new Tile(0);
            }
        }
    }

    /**
     * Get the size of the game board
     *
     * @return the size of game board
     */
    public Integer getSize() {
        return 4;
    }

    /**
     * Check if the given position is inside the game board
     *
     * @param pos
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
     * @param pos
     * @return the tile at position
     */
    public Tile getTile(Position pos) {
        return tiles[pos.getRow()][pos.getColumn()];
    }

    /**
     * Checks if the given tile can be placed in the given position while
     * respecting the rules.
     *
     * @param tile
     * @param pos
     * @return true if the tile can be placed, false otherwise
     */
    public boolean canBePut(Tile tile, Position pos) {
        //TO DO
        return true;
    }

    /**
     * Place a given tile at a specific position
     *
     * @param tile
     * @param pos
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

        return false;
        //TO DOOOOO
    }

    @Override
    public String toString() {
        return Arrays.deepToString(tiles);
        //TO COMPLETE AND MODIFYE
    }

}
