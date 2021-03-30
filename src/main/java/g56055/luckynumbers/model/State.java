package g56055.luckynumbers.model;

/**
 * Different possible states of the game
 * 
 * @author Duran Rehan g56055
 */
public enum State {

    /**
     * Initial state of the game
     */
    NOT_STARTED,
    /**
     * State of the tile selection
     */
    PICK_TILE,
    /**
     * Tile placement state
     */
    PLACE_TILE,
    /**
     * End of game state
     */
    GAME_OVER,
    /**
     * Game turn rotation
     */
    TURN_END
}
