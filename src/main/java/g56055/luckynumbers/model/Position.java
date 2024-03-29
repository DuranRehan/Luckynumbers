package g56055.luckynumbers.model;

/**
 * Determine a precise position on the game board
 *
 * @author Duran Rehan g56055
 */
public class Position {

    private int row;
    private int column;

    /**
     * Define a position on the game board
     *
     * @param row the row of the game board
     * @param column the column of the game board
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get the value of column
     *
     * @return the value of column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Get the value of row
     *
     * @return the value of row
     */
    public int getRow() {
        return row;
    }

}
