package g56055.luckynumbers.model;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for Deck class
 *
 * @author Duran Rehan g56055
 */
public class DeckTest {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck(2);
    }

    /* =====================
         Tests for faceDownCount()
       ========================= */
 /*Turn all tiles face up*/
    private void removeAllHiddenTiles() {
        int i = deck.faceDownCount();
        while (i > 0) {
            deck.pickFaceDown();
            i--;
        }
    }

    @Test
    public void faceDownCount_when_all_value_are_hidden() {
        assertEquals(40, deck.faceDownCount());
    }

    @Test
    public void faceDownCount_when_all_value_are_visible() {
        removeAllHiddenTiles();
        assertEquals(0, deck.faceDownCount());
    }

    /* =====================
         Tests for pickFaceDown()
       ========================= */
    @Test
    public void pickFaceDown_remove_first_tile() {
        Tile tile_candidate = deck.getFaceDownTiles().get(0);
        assertEquals(tile_candidate, deck.pickFaceDown());
    }

    /* =====================
         Tests for faceUpCount()
       ========================= */
    @Test
    public void faceUpCount_all_tiles_visible() {
        for (int i = 0; i < 40; i++) {
            Tile tile = new Tile(5);
            deck.putBack(tile);
        }
        assertEquals(40, deck.faceUpCount());
    }

    @Test
    public void faceUpCount_no_tiles_is_visible() {
        assertEquals(0, deck.faceUpCount());
    }

    /* =====================
         Tests for hasFaceUp()
       ========================= */
    @Test
    public void hasFaceUp_when_tile_Not_exists_but_visible() {
        Tile tile_candidate = new Tile(25);
        tile_candidate.flipFaceUp();
        assertFalse(deck.hasFaceUp(tile_candidate));
    }

    @Test
    public void hasFaceUp_when_tile_exists_but_not_visible() {
        Tile tile_candidate = new Tile(2);
        assertFalse(deck.hasFaceUp(tile_candidate));
    }

    @Test
    public void hasFaceUp_when_tile_not_exists_and_not_visible() {
        Tile tile_candidate = new Tile(25);
        assertFalse(deck.hasFaceUp(tile_candidate));
    }

    @Test
    public void hasFaceUp_when_tile_exists_and_visible() {
        Tile tile_candidate = new Tile(2);
        deck.putBack(tile_candidate);
        assertTrue(deck.hasFaceUp(tile_candidate));
    }

    /* =====================
         Tests for pickFaceUp()
       ========================= */
    @Test
    public void pickFaceUp_remove_value_correctly() {
        Tile tile_candidate = new Tile(25);
        deck.putBack(tile_candidate);
        deck.pickFaceUp(tile_candidate);
        assertFalse(deck.getAllFaceUp().contains(tile_candidate));
    }

    /* =====================
         Tests for putBack ()
       ========================= */
    @Test
    public void putBack_check_if_visible_ok() {
        Tile tile_candidate = new Tile(2);
        deck.putBack(tile_candidate);
        assertTrue(deck.getAllFaceUp().get(0).isFaceUp());
    }

    @Test
    public void putBack_is_in_face_up_list() {
        Tile tile_candidate = new Tile(2);
        deck.putBack(tile_candidate);
        assertEquals(tile_candidate, deck.getAllFaceUp().get(0));
    }

    /* =====================
         Tests for pickRandomDownTile ()
       ========================= */
    @Test
    public void pickRandomDownTile_list_are_ascending() {
        List<Tile> list = deck.pickRandomDownTile(4);
        int valPos1 = list.get(0).getValue();
        int valPos2 = list.get(1).getValue();
        int valPos3 = list.get(2).getValue();
        int valPos4 = list.get(3).getValue();
        assertTrue(
                valPos1 < valPos2
                && valPos2 < valPos3
                && valPos3 < valPos4
        );
    }

    @Test
    public void pickRandomDownTile_value_in_list_not_equals() {
        List<Tile> list = deck.pickRandomDownTile(4);
        boolean isEquals = false;
        for (Tile tile : list) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != tile
                        && tile.getValue() == list.get(i).getValue()) {
                    isEquals = true;
                }
            }
        }
        assertFalse(isEquals);
    }
}
