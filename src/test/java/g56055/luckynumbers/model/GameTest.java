package g56055.luckynumbers.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author MCD <mcodutti@he2b.be> 
 * @author Duran Rehan
 * 
 * Test class for Game class
 */
public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    /* =====================
         Tests for start()
       ===================== */

 /* --- test related to the state --- */
    @Test
    public void start_when_game_not_started_ok() {
        game.start(4);
    }

    @Test
    public void start_when_game_over_ok() {
        fullPlay();
        game.start(2);
    }

    /* Play a game till the end */
    private void fullPlay() {
        game.start(2);
        int value = 1;
        int line = 0;
        int col = 0;
        for (int turn = 1; turn < game.getBoardSize() * game.getBoardSize(); turn++) {
            for (int player = 0; player < game.getPlayerCount(); player++) {
                game.pickTile(value);
                game.putTile(new Position(line, col));
                game.nextPlayer();
            }
            value++;
            col++;
            if (col == game.getBoardSize()) {
                col = 0;
                line++;
            }
        }
        game.pickTile(20);
        game.putTile(new Position(line, col));
    }

    @Test
    public void start_when_game_in_progress_ISE() {
        game.start(4);
        assertThrows(IllegalStateException.class,
                () -> game.start(1));
    }

    @Test
    public void start_state_changed_to_PICK_TILE() {
        game.start(3);
        assertEquals(State.PICK_TILE, game.getState());
    }

    /* --- tests related to the parameter --- */
    @Test
    public void start_playerCount_too_small_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(1));
    }

    @Test
    public void start_playerCount_minimum_accepted() {
        game.start(2);
    }

    @Test
    public void start_playerCount_maximum_accepted() {
        game.start(4);
    }

    @Test
    public void start_playerCount_too_big_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(5));
    }

    /* -- tests related to fields initialization --- */
    @Test
    public void start_playerCount_initialized() {
        game.start(4);
        assertEquals(4, game.getPlayerCount());
    }

    @Test
    public void start_current_player_is_player_0() {
        game.start(4);
        assertEquals(0, game.getCurrentPlayerNumber());
    }

    /* === À vous de compléter... === */
 /* =====================
         Tests for PickTile()
       ===================== */
    @Test
    public void pickTile_change_state_in_PLACE_TILE_ok() {
        game.start(2);
        game.pickTile(4);
        assertEquals(State.PLACE_TILE, game.getState());
    }

    @Test
    public void pickTile_when_state_is_not_ok() {
        game.start(2);
        game.pickTile(5);
        assertThrows(IllegalStateException.class,
                () -> game.pickTile(5));
    }

    @Test
    public void pickTile_value_in_tile_is_Equal_to_another() {
        game.start(2);
        Tile tile = game.pickTile(4);
        assertEquals(new Tile(4), tile);
    }

    @Test
    public void pickTile_check_if_pickTile_is_the_pickTile() {
        game.start(2);
        Tile Tile_candidate = game.pickTile(4);
        assertEquals(Tile_candidate, game.getPickedTile());
    }

    /* =====================
         Tests for putTile()
       ===================== */
    @Test
    public void putTile_change_state_in_GAME_OVER() {
        fullPlay();
        assertEquals(State.GAME_OVER, game.getState());
    }

    @Test
    public void putTile_change_state_in_TURN_END() {
        game.start(2);
        game.pickTile(5);
        game.putTile(new Position(0, 2));
        assertEquals(State.TURN_END, game.getState());
    }

    @Test
    public void putTile_when_state_is_not_ok() {
        game.start(2);
        game.pickTile(5);
        game.putTile(new Position(0, 1));
        assertThrows(IllegalStateException.class,
                () -> game.putTile(new Position(0, 1)));
    }

    @Test
    public void putTile_when_tile_cant_be_place() {
        game.start(2);
        game.pickTile(5);
        assertThrows(IllegalArgumentException.class,
                () -> game.putTile(new Position(10, 1)));
    }

    /* =====================
         Tests for nextPlayer()
       ===================== */
    @Test
    public void nextPlayer_change_state_in_PICK_TILE() {
        game.start(2);
        game.pickTile(5);
        game.putTile(new Position(0, 1));
        game.nextPlayer();
        assertEquals(State.PICK_TILE, game.getState());
    }

    @Test
    public void nextPlayer_when_state_is_not_ok() {
        game.start(2);
        game.pickTile(5);
        game.putTile(new Position(0, 1));
        game.nextPlayer();
        assertThrows(IllegalStateException.class,
                () -> game.nextPlayer());
    }

    @Test
    public void nextPlayer_change_player_ok() {
        game.start(2);
        game.pickTile(5);
        game.putTile(new Position(0, 1));
        game.nextPlayer();
        int currentCandidate = game.getCurrentPlayerNumber();
        int currentPlayerExpected = 1;
        assertEquals(currentPlayerExpected, currentCandidate);
    }

    @Test
    public void nextPlayer_is_Last() {
        game.start(4);
        int expectedCurrent = 0;
        assertEquals(expectedCurrent, nextCurrentPlayer(4));
    }

    /* Change current Player n times */
    private int nextCurrentPlayer(int n) {
        for (int i = 0; i < n; i++) {
            game.pickTile();
            game.putTile(new Position(0, 1));
            game.nextPlayer();
        }
        return game.getCurrentPlayerNumber();
    }

    /* =====================
         Tests for getPlayerCount()
       ===================== */
    @Test
    public void getPlayerCount_game_IS_NOT_STARTED() {
        assertThrows(IllegalStateException.class,
                () -> game.getPlayerCount());
    }

    /* ====================================
         Tests for getCurrentPlayerNumber()
       =====================================*/
    @Test
    public void getCurrentPlayerNumber_game_NOT_STARTED() {
        assertThrows(IllegalStateException.class,
                () -> game.getCurrentPlayerNumber());
    }

    @Test
    public void getCurrentPlayerNumber_game_IS_GAME_OVER() {
        fullPlay();
        assertThrows(IllegalStateException.class,
                () -> game.getCurrentPlayerNumber());
    }

    /* ==========================
         Tests for getPickedTile()
       ===========================*/
    @Test
    public void getPickedTile_when_state_is_NOT_Ok() {
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.getPickedTile());

    }

    /* ==========================
         Tests for canTileBePut()
       ===========================*/
    @Test
    public void canTileBePut_when_state_is_NOT_Ok() {
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.canTileBePut(new Position(1, 1)));

    }

    @Test
    public void canTileBePut_when_position_is_NOT_Ok() {
        game.start(2);
        game.pickTile();
        game.putTile(new Position(1, 1));
        assertThrows(IllegalStateException.class,
                () -> game.canTileBePut(new Position(10, 1)));
    }

    /* ====================
         Tests for getTile()
       ======================*/
    @Test
    public void getTile_when_state_is_NOT_STARTED() {
        assertThrows(IllegalStateException.class,
                () -> game.getTile(0, new Position(1, 2)));
    }

    @Test
    public void getTile_when_position_is_NOT_inside() {
        game.start(2);
        game.pickTile();
        game.putTile(new Position(1, 1));
        assertThrows(IllegalArgumentException.class,
                () -> game.getTile(0, new Position(10, 2)));
    }

    @Test
    public void getTile_when_playerNumber_is_outRange() {
        game.start(2);
        game.pickTile();
        game.putTile(new Position(1, 1));
        assertThrows(IllegalArgumentException.class,
                () -> game.getTile(5, new Position(1, 2)));
    }

    @Test
    public void getTile_when_playerNumber_is_outRange_lower_0() {
        game.start(2);
        game.pickTile();
        game.putTile(new Position(1, 1));
        assertThrows(IllegalArgumentException.class,
                () -> game.getTile(-1, new Position(1, 2)));
    }

    /* ====================
         Tests for getWinner()
       ======================*/
    @Test
    public void getWinner_when_state_is_not_ok() {
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.getWinner());
    }
}
