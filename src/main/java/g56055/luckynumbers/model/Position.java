package g56055.luckynumbers.model;

/**
 *
 * @author Duran Rehan g56055, Determine a precise position on the game board
 */
public class Position {

    private final Integer row;
    private final Integer column;

    /**
     * Constructor for Postion class
     *
     * @param row
     * @param column
     */
    public Position(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get the value of column
     *
     * @return the value of column
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * Get the value of row
     *
     * @return the value of row
     */
    public Integer getRow() {
        return row;
    }

}
