package g56055.luckynumbers.model;

/**
 * Define the game board on which the player will play
 *
 * @author Duran Rehan g56055
 */
public class Board {

    private Tile[][] tiles;

    /**
     * define a empty game board with a size of 4 x 4
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
     * @return true if the position is inside the board, false otherwise
     */
    public boolean isInside(Position pos) {
        return (pos.getRow() <= getSize() - 1 && pos.getRow() >= 0)
                && (pos.getColumn() <= getSize() - 1 && pos.getColumn() >= 0);

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
        return isRespectingRules(tile, pos);
    }

    /**
     * Check from its initial position to the right edge of the game board if
     * all the tiles are greater than the one you want to put
     *
     * @param tile the tile you want to check
     * @param pos the position to check
     * @return true if the tile at position are lower than others, false
     * otherwise
     */
    private boolean canBePutIn_Row_Right(Tile tile, Position pos) {
        int lg = pos.getRow();
        int col = pos.getColumn() + 1;
        Position posCandidate = new Position(lg, col);
        while (isInside(posCandidate)) {
            if (tiles[lg][col] == null) {
                posCandidate = new Position(lg, col + 1);
                col += 1;
            } else {
                return tiles[lg][col].getValue() > tile.getValue();
            }
        }
        return true;
    }

    /**
     * Check from its initial position to the left edge of the game board if all
     * the tiles are lower than the one you want to put
     *
     * @param tile the tile you want to check
     * @param pos the position to check
     * @return true if the tile at position are greater than others, false
     * otherwise
     */
    private boolean canBePutIn_Row_Left(Tile tile, Position pos) {
        int lg = pos.getRow();
        int col = pos.getColumn() - 1;
        Position posCandidate = new Position(lg, col);
        while (isInside(posCandidate)) {
            if (tiles[lg][col] == null) {
                posCandidate = new Position(lg, col - 1);
                col -= 1;
            } else {
                return tiles[lg][col].getValue() < tile.getValue();
            }
        }
        return true;
    }

    /**
     * Check from its initial position to the upper edge of the game board if
     * all the tiles are lower than the one you want to put
     *
     * @param tile the tile you want to check
     * @param pos the position to check
     * @return true if the tile at position are greater than others, false
     * otherwise
     */
    private boolean canBePutIn_Col_Up(Tile tile, Position pos) {
        int lg = pos.getRow() + 1;
        int col = pos.getColumn();
        Position posCandidate = new Position(lg, col);
        while (isInside(posCandidate)) {
            if (tiles[lg][col] == null) {
                posCandidate = new Position(lg + 1, col);
                lg += 1;
            } else {
                return tiles[lg][col].getValue() > tile.getValue();
            }
        }
        return true;
    }

    /**
     * Check from its initial position to the lower edge of the game board if
     * all the tiles are greater than the one you want to put
     *
     * @param tile the tile you want to check
     * @param pos the position to check
     * @return true if the tile at position are lower than others, false
     * otherwise
     */
    private boolean canBePutIn_Col_Down(Tile tile, Position pos) {
        int lg = pos.getRow() - 1;
        int col = pos.getColumn();
        Position posCandidate = new Position(lg, col);
        while (isInside(posCandidate)) {
            if (tiles[lg][col] == null) {
                posCandidate = new Position(lg - 1, col);
                lg -= 1;
            } else {
                return tiles[lg][col].getValue() < tile.getValue();
            }
        }
        return true;
    }

    /**
     * Check if the tile you want to place respects the rules of the game
     *
     * @param tile the tile you want to check
     * @param pos the position you want to place the tile
     * @return true if the tile at position respect the rules, false otherwise
     */
    private boolean isRespectingRules(Tile tile, Position pos) {
        return canBePutIn_Row_Right(tile, pos)
                && canBePutIn_Row_Left(tile, pos)
                && canBePutIn_Col_Up(tile, pos)
                && canBePutIn_Col_Down(tile, pos);
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
    /**
     * Count the number of cases currently empty on the board
     * 
     * @return the count of empty cases of the board
     */
    public int countEmptyCases() {
        int countEmpty = 0;
        for (Tile[] lg : tiles) {
            for (Tile col : lg) {
                if (col == null) {
                    countEmpty++;
                }
            }
        }
        return countEmpty;
    }
}
