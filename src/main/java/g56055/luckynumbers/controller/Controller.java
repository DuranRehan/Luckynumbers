package g56055.luckynumbers.controller;

import g56055.luckynumbers.model.Model;
import g56055.luckynumbers.model.Position;
import g56055.luckynumbers.view.View;

/**
 * Gathers and controls the different elements of the model and the view.
 *
 * @author Duran Rehan g56055
 */
public class Controller {

    private final Model game;
    private final View view;

    /**
     * Define a game with its view
     *
     * @param game
     * @param view
     */
    public Controller(Model game, View view) {
        this.game = game;
        this.view = view;
    }

    /**
     * Manage the progress of a game of LuckyNumbers
     */
    public void play() {
        view.displayWelcome();
        while (true) {
            switch (game.getState()) {
                case NOT_STARTED:
                    int playerCount = view.askPlayerCount();
                    notStartedStateControls(playerCount);
                    break;

                case PICK_TILE:
                    game.pickTile();
                    view.displayGame();
                    break;

                case PLACE_TILE:
                    Position pos = view.askPosition();
                    placeTileState(pos);
                    break;
                case TURN_END:
                    game.nextPlayer();
                    break;
                case GAME_OVER:
                    view.displayWinner();
                    break;
            }
        }
    }

    /**
     * Control the beginning state of the LuckyNumbers game
     *
     * @param playerCount Number of player in the game
     */
    private void notStartedStateControls(int playerCount) {
        try {
            game.start(playerCount);
        } catch (IllegalArgumentException e) {
            view.displayError("The game required min 2 players and max 4 !");
        }
    }

    /**
     * Control the placement of a tile during a game of Lucky Numbers
     *
     * @param pos position of the tile
     */
    private void placeTileState(Position pos) {
        try {
            game.putTile(pos);
        } catch (IllegalArgumentException e) {
            while (!game.canTileBePut(pos)) {
                view.displayError("The tile violated the rule !");
                pos = view.askPosition();
            }
            game.putTile(pos);
        }
    }
}
