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
        for (int i = 0; i < 31; i++) {
            game.pickFaceDownTile();
            game.dropTile();
            game.nextPlayer();
        }
        game.pickFaceDownTile();
        game.putTile(new Position(3, 3));
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
//    @Test
//    public void pickTile_change_state_in_PLACE_TILE_ok() {
//        game.start(2);
//        game.pickTile();
//        assertEquals(State.PLACE_TILE, game.getState());
//    }
//
//    @Test
//    public void pickTile_when_state_is_not_ok() {
//        game.start(2);
//        game.pickTile();
//        assertThrows(IllegalStateException.class,
//                () -> game.pickTile());
//    }
//
//    @Test
//    public void pickTile_check_if_pickTile_is_the_pickedTile() {
//        game.start(2);
//        Tile Tile_candidate = game.pickTile();
//        assertEquals(Tile_candidate, game.getPickedTile());
//    }

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
        game.pickChosenFaceDownTile(0);
        game.putTile(new Position(0, 0));
        assertEquals(State.TURN_END, game.getState());
    }

    @Test
    public void putTile_when_state_is_not_ok() {
        game.start(2);
        game.pickChosenFaceDownTile(0);
        game.putTile(new Position(0, 0));
        assertThrows(IllegalStateException.class,
                () -> game.putTile(new Position(0, 1)));
    }

    @Test
    public void putTile_when_tile_cant_be_place() {
        game.start(2);
        game.pickFaceDownTile();
        assertThrows(IllegalArgumentException.class,
                () -> game.putTile(new Position(10, 1)));
    }

    /* =====================
         Tests for nextPlayer()
       ===================== */
    @Test
    public void nextPlayer_change_state_in_PICK_TILE() {
        game.start(2);
        game.pickFaceDownTile();
        game.putTile(new Position(3, 3));
        game.nextPlayer();
        assertEquals(State.PICK_TILE, game.getState());
    }

    @Test
    public void nextPlayer_when_state_is_not_ok() {
        game.start(2);
        game.pickChosenFaceDownTile(0);
        game.putTile(new Position(0, 0));
        game.nextPlayer();
        assertThrows(IllegalStateException.class,
                () -> game.nextPlayer());
    }

    @Test
    public void nextPlayer_change_player_ok() {
        game.start(2);
        game.pickChosenFaceDownTile(0);
        game.putTile(new Position(0, 0));
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
            game.pickFaceDownTile();
            game.dropTile();
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
        game.pickFaceDownTile();
        game.putTile(new Position(1, 1));
        assertThrows(IllegalStateException.class,
                () -> game.canTileBePut(new Position(10, 1)));
    }

    @Test
    public void canTileBePut_when_Empty_Board() {
        game.start(2);
        game.pickFaceDownTile();
        assertTrue(game.canTileBePut(new Position(1, 1)));
    }

    @Test
    public void canTileBePut_when_respect_rule() {
        game.start(2);
        game.pickChosenFaceDownTile(
                game.getTile(0, new Position(0, 0)).getValue() + 1);
        assertTrue(game.canTileBePut(new Position(0, 3)));
    }

    @Test
    public void canTileBePut_when_respect_rule_And_occupied() {
        game.start(2);
        game.pickChosenFaceDownTile(
                game.getTile(0, new Position(0, 0)).getValue() + 2);
        game.putTile(new Position(0, 3));
        game.nextPlayer();

        game.pickChosenFaceDownTile(0);
        game.putTile(new Position(0, 0));
        game.nextPlayer();

        game.pickChosenFaceDownTile(
                game.getTile(0, new Position(0, 0)).getValue() + 1);
        assertTrue(game.canTileBePut(new Position(0, 3)));

    }

    @Test
    public void canTileBePut_when_not_respect_rule() {
        game.start(2);
        game.pickChosenFaceDownTile(25);
        assertFalse(game.canTileBePut(new Position(0, 1)));
    }

    @Test
    public void canTileBePut_when_same_value_right() {
        game.start(2);
        Tile candidate = game.getTile(0, new Position(0, 0));
        game.pickChosenFaceDownTile(candidate.getValue());
        assertFalse(game.canTileBePut(new Position(0, 1)));
    }

    @Test
    public void canTileBePut_when_same_value_down() {
        game.start(2);
        Tile candidate = game.getTile(0, new Position(1, 1));
        game.pickChosenFaceDownTile(candidate.getValue());
        assertFalse(game.canTileBePut(new Position(2, 1)));
    }

    @Test
    public void canTileBePut_when_same_value_left() {
        game.start(2);
        Tile candidate = game.getTile(0, new Position(1, 1));
        game.pickChosenFaceDownTile(candidate.getValue());
        assertFalse(game.canTileBePut(new Position(1, 0)));
    }

    @Test
    public void canTileBePut_when_same_value_up() {
        game.start(2);
        Tile candidate = game.getTile(0, new Position(3, 3));
        game.pickChosenFaceDownTile(candidate.getValue());
        assertFalse(game.canTileBePut(new Position(2, 3)));
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
        game.pickFaceDownTile();
        game.putTile(new Position(1, 1));
        assertThrows(IllegalArgumentException.class,
                () -> game.getTile(0, new Position(10, 2)));
    }

    @Test
    public void getTile_when_playerNumber_is_outRange() {
        game.start(2);
        game.pickFaceDownTile();
        game.putTile(new Position(1, 1));
        assertThrows(IllegalArgumentException.class,
                () -> game.getTile(5, new Position(1, 2)));
    }

    @Test
    public void getTile_when_playerNumber_is_outRange_lower_0() {
        game.start(2);
        game.pickFaceDownTile();
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
                () -> game.getWinners());
    }

    @Test
    public void getWinner_state_is_ok() {
        fullPlay();
        assertTrue(game.getState() == State.GAME_OVER);
    }

    @Test
    public void getWinner_winner_is_the_right_player() {
        fullPlay();
        int winner = 0;
        assertTrue(winner == game.getWinners().get(0));
    }

    /* ====================
         Tests for pickFaceDownTile()
       ======================*/
    @Test
    public void pickFaceDownTile_when_state_not_OK() {
        assertThrows(IllegalStateException.class,
                () -> game.pickFaceDownTile());
    }

    @Test
    public void pickFaceDownTile_change_state_in_PLACE_OR_DROP_TILE() {
        game.start(2);
        game.pickFaceDownTile();
        assertEquals(State.PLACE_OR_DROP_TILE, game.getState());
    }

    @Test
    public void pickFaceDownTile_Tile_is_the_picked_tile() {
        game.start(2);
        Tile tile_candidate = game.pickFaceDownTile();
        assertEquals(game.getPickedTile(), tile_candidate);
    }

    /* ====================
         Tests for pickFaceUpTile()
       ======================*/
    @Test
    public void pickFaceUpTile_when_state_not_OK() {
        assertThrows(IllegalStateException.class,
                () -> game.pickFaceUpTile(new Tile(5)));
    }

    @Test
    public void pickFaceUpTile_change_state_in_PLACE_TILE() {
        game.start(2);
        game.pickFaceUpTile(new Tile(5));
        assertEquals(State.PLACE_TILE, game.getState());
    }

    @Test
    public void pickFaceUpTile_Tile_is_the_picked_tile() {
        game.start(2);
        Tile tile_candidate = game.pickFaceUpTile(new Tile(5));
        assertEquals(game.getPickedTile(), tile_candidate);
    }

    /* ====================
         Tests for dropTile()
       ======================*/
    @Test
    public void dropTile_when_state_not_OK() {
        assertThrows(IllegalStateException.class,
                () -> game.dropTile());
    }

    @Test
    public void dropTile_change_state_in_TURN_END() {
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        assertEquals(State.TURN_END, game.getState());
    }

    /* ====================
         Tests for faceDownTileCount()
       ======================*/
    @Test
    public void faceDownTileCount_state_not_ok() {
        assertThrows(IllegalStateException.class,
                () -> game.faceDownTileCount());
    }

    @Test
    public void faceDownTileCount_all_tile_face_down() {
        game.start(2);
        assertEquals(32, game.faceDownTileCount());
    }

    /* ====================
         Tests for faceUpTileCount()
       ======================*/
    @Test
    public void faceUpTileCount_state_not_ok() {
        assertThrows(IllegalStateException.class,
                () -> game.faceUpTileCount());
    }

    @Test
    public void faceUpTileCount_all_tile_face_Up() {
        game.start(2);
        int playerCount = game.getPlayerCount();
        addTilesInFaceUpList(20 * playerCount - playerCount * 4);
        assertEquals(32, game.faceUpTileCount());
    }

    /*Add n values in the face up list*/
    private void addTilesInFaceUpList(int n) {
        for (int i = 0; i < n; i++) {
            game.pickFaceDownTile();
            game.dropTile();
            game.nextPlayer();
        }
    }

    /* ====================
         Tests for getAllfaceUpTiles()
       ======================*/
    @Test
    public void getAllfaceUpTiles_state_not_ok() {
        assertThrows(IllegalStateException.class,
                () -> game.getAllfaceUpTiles());
    }

    @Test
    public void getAllfaceUpTiles_list_on_start() {
        game.start(2);
        assertEquals(0, game.getAllfaceUpTiles().size());
    }

    @Test
    public void getAllfaceUpTiles_list_on_end() {
        fullPlay();
        assertEquals(31, game.getAllfaceUpTiles().size());
    }

    @Test
    public void getAllfaceUpTiles_list_size_equals_count_Face_Up_tiles() {
        game.start(2);
        addTilesInFaceUpList(25);
        int totalFaceUp = game.faceUpTileCount();
        assertEquals(totalFaceUp, game.getAllfaceUpTiles().size());
    }

    /* ====================
         Tests for placeTileBeginning()
       ======================*/
    @Test
    public void placeTileBeginning_state_not_ok() {
        assertThrows(IllegalStateException.class,
                () -> game.placeTileBeginning());
    }

    @Test
    public void placeTileBeginning_tiles_are_placed_on_diagonale() {
        game.start(2);
        game.placeTileBeginning();
        assertTrue(
                (game.getTile(0, new Position(0, 0)) != null)
                && (game.getTile(0, new Position(1, 1)) != null)
                && (game.getTile(0, new Position(2, 2)) != null)
                && (game.getTile(0, new Position(3, 3)) != null)
        );
    }

    @Test
    public void placeTileBeginning_tiles_are_in_ascending_order() {
        game.start(2);
        game.placeTileBeginning();
        int valPos1 = game.getTile(0, new Position(0, 0)).getValue();
        int valPos2 = game.getTile(0, new Position(1, 1)).getValue();
        int valPos3 = game.getTile(0, new Position(2, 2)).getValue();
        int valPos4 = game.getTile(0, new Position(3, 3)).getValue();
        assertTrue(
                valPos1 < valPos2
                && valPos2 < valPos3
                && valPos3 < valPos4
        );
    }
}
