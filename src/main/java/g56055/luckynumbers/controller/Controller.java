package g56055.luckynumbers.controller;

import g56055.luckynumbers.model.Model;
import g56055.luckynumbers.model.Position;
import g56055.luckynumbers.model.State;
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
                    break;

                case PICK_TILE:
                    game.pickTile();
                    view.displayGame();
                    break;

                case PLACE_TILE:                   
                    Position pos = view.askPosition();
                    game.putTile(pos);
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
}
