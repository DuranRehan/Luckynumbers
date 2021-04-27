package g56055.luckynumbers.model;

import java.util.List;

/**
 * Interface for the Game model.
 *
 * @author MCD {@literal <mcodutti@he2b.be>}
 * @author Duran Rehan g56055
 */
public interface Model {

    /**
     * Initialize a game.
     * <ul>
     * <li>An empty board is created for each player</li>
     * <li>Player number 0 starts the game</li>
     * <li>State becomes PICK_TILE</li>
     * </ul>
     *
     * @param playerCount the number of players
     * @throws IllegalArgumentException if the number of players is not between
     * 2 and 4 (both included).
     * @throws IllegalStateException if called when state is not NOT_STARTED nor
     * GAME_OVER.
     */
    void start(int playerCount);

    /**
     * Give the size of the boards. We suppose that all boards are squares and
     * of the same size. So this is both number of lines and number of columns.
     * With the official rules, this should be 4 but this must not be assumed
     * and this methode must be used instead of hardcoding that value elsewhere
     * in the code.
     *
     * @return the size of the board.
     */
    int getBoardSize();

    /**
     * The current player pick a random face down tile in the deck.
     * <p>
     * State becomes : </p>
     * <ul>
     * <li>PLACE_OR_DROP_TILE.</li>
     * </ul>
     *
     * @return a random picked tile of the deck
     * @throws IllegalStateException if called when state is not PICK_TILE
     */
    Tile pickFaceDownTile();

    /**
     * The current player pick a face up tile of the deck.
     * <p>
     * State becomes : </p>
     * <ul>
     * <li>PLACE_TILE.</li>
     * </ul>
     *
     * @param tile the picked tile
     * @return a chosen tile from face up deck
     * @throws IllegalStateException if called when state is not PICK_TILE
     */
    Tile pickFaceUpTile(Tile tile);

    /**
     * Put back the previously chosen tile to the deck, face up
     *
     * <p>
     * State becomes : </p>
     * <ul>
     * <li>TURN_END.</li>
     * </ul>
     *
     * @throws IllegalStateException if called when state is not
     * PLACE_OR_DROP_TILE
     */
    void dropTile();

    /**
     * Give the number of face down tiles remaining in the deck
     *
     * @return the number of face down tiles
     * @throws IllegalArgumentException if state is NOT_STARTED
     */
    int faceDownTileCount();

    /**
     * Give the number of face up tiles in the deck
     *
     * @return the number of face up tiles
     * @throws IllegalArgumentException if state is NOT_STARTED
     */
    int faceUpTileCount();

    /**
     * Give the list of face up tiles in the deck
     *
     * @return list of all face up tiles
     * @throws IllegalArgumentException if state is NOT_STARTED
     */
    List<Tile> getAllfaceUpTiles();

    /**
     * Put a tile at the given position on the current Player board.
     * <p>
     * State becomes : </p>
     * <ul>
     * <li>TURN_END.</li>
     * OR
     * <li>GAME_OVER</li>
     * </ul>
     *
     * @param pos where to put the tile.
     * @throws IllegalArgumentException if the tile can't be put on that
     * position (position outside of the board or position not allowed by the
     * rules)
     * @throws IllegalStateException if called when state is not PLACE_TILE or
     * PLACE_OR_DROP_TILE
     */
    void putTile(Position pos);

    /**
     * Change current player. The next player becomes the current one. The order
     * is : 0, 1, 2, 3 and again 0, 1, ... State becomes PICK_TILE
     *
     * @throws IllegalStateException if called when state is not TURN_END
     */
    void nextPlayer();

    /**
     * Give the total number of players in this game.
     *
     * @return the total number of players in this game.
     * @throws IllegalArgumentException if state is NOT_STARTED
     */
    int getPlayerCount();

    /**
     * Get the current state of the game.
     *
     * @return the current state of the game.
     */
    State getState();

    /**
     * Give the number of the current player. Players are numeroted from 0 to
     * (count-1).
     *
     * @return the number of the current player.
     * @throws IllegalArgumentException if state is NOT_STARTED or GAME_OVER
     */
    int getCurrentPlayerNumber();

    /**
     * Get the current picked tile. Get the tile picked by the current player.
     *
     * @return the current picked tile.
     * @throws IllegalStateException if state is not PLACE_TILE
     */
    Tile getPickedTile();

    /**
     * Check if a position is inside the board of the current player or not.
     *
     * @param pos a position
     * @return true if the given position is inside the board.
     */
    boolean isInside(Position pos);

    /**
     * Check if a tile can be put at the given position. Check if the current
     * player is allowed to put its previously picked tile at the given position
     * on the board of the current player.
     *
     * @param pos the position to check
     * @return true if the picked tile can be put at that position.
     * @throws IllegalArgumentException if the position is outside the board.
     * @throws IllegalStateException if state is not PLACE_TILE.
     */
    boolean canTileBePut(Position pos);

    /**
     * Give a tile at a given position of the board of a given player.
     *
     * @param playerNumber the player number
     * @param pos a position on the board
     * @return the tile at that position for that player or <code>null</code> if
     * there is no tile there.
     * @throws IllegalArgumentException if the position is outside the board or
     * if the playerNUmber is ouside of range.
     * @throws IllegalStateException if game state is NOT_STARTED
     */
    Tile getTile(int playerNumber, Position pos);

    /**
     * Give the list of winners.
     *
     * @return the list of winners.
     * @throws IllegalStateException if game state is not GAME_OVER
     */
    List<Integer> getWinners();

    /**
     * Place 4 random tiles in ascending order on each player's board
     *
     * @throws IllegalStateException if the state is not PICK_TILE
     */
    void placeTileBeginning();
}
