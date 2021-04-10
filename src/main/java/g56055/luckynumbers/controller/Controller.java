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

    private Model game;
    private View view;

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
                    game.start(playerCount);
                    game.placeTileBeginning();
                    break;

                case PICK_TILE:
                    view.askDownOrUp();
                    break;
                case PLACE_OR_DROP_TILE:
                    Place_Or_Drop_tile_control();
                    break;
                case PLACE_TILE:
                    view.displayGame();
                    Position pos = view.askPosition();
                    game.putTile(pos);
                    break;
                case TURN_END:
                    game.nextPlayer();
                    break;
                case GAME_OVER:
                    view.displayWinner();
                    game.start(view.askPlayerCount());
                    break;
            }
        }
    }

    /**
     * Controls state actions of PLACE_OR_DROP_TILE
     */
    private void Place_Or_Drop_tile_control() {
        view.displayGame();
        if (game.faceDownTileCount() == 0) {
            Position pos = view.askPosition();
            game.putTile(pos);
        } else {
            if (view.askDropOrNot()) {
                game.dropTile();
            } else {
                Position pos = view.askPosition();
                game.putTile(pos);
            }
        }
    }
}
