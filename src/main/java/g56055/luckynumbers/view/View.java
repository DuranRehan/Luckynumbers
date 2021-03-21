/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56055.luckynumbers.view;

import g56055.luckynumbers.model.Position;

/**
 *
 * @author Duran Rehan g56055
 *
 * Interface for the Game view.
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
     * Manage the display of game status
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
     * asks the user to enter a
     * <ul>
     * <li>line number</li>
     * <li>Column number</li>
     * </ul>
     *
     * @throws IllegalArgumentException if Position is not valide
     *
     * @return One row and one column as a position.
     */
    Position askPosition();

}
