package g56055.luckynumbers.view;

import g56055.luckynumbers.model.Position;
import g56055.luckynumbers.model.Tile;

/**
 * Interface for the game view.
 *
 * @author Duran Rehan g56055
 */
public interface View {

    /**
     * Displays information related to the game
     * <ul>
     * <li>Name of the game</li>
     * <li>Name of the Author</li>
     * <li>Version of the game</li>
     * </ul>
     */
    void displayWelcome();

    /**
     * Manage the display of game boards
     * <ul>
     * <li>Current player</li>
     * <li>Board of the current player</li>
     * <li>The picked tile</li>
     * </ul>
     */
    void displayGame();

    /**
     * Displays the number of the winning player.
     */
    void displayWinner();

    /**
     * Displays the error message given.
     *
     * @param message error message
     */
    void displayError(String message);

    /**
     * Asks how many players are in the game.
     *
     * @return The count of player in the game
     */
    int askPlayerCount();

    /**
     * asks the user to enter a valide
     * <ul>
     * <li>line number</li>
     * <li>Column number</li>
     * </ul>
     *
     * @return One row and one column as a position.
     */
    Position askPosition();
    
    /**
     * Ask to user if he want pick a tile in face down deck or in the face up
     */
    void askDownOrUp();
    
    /**
     * Ask user if he want drop the picked tile 
     * @return true if user would drop the tile, false otherwise
     */
    boolean askDropOrNot();
}
