package g56055.luckynumbers.model;

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
    private void flipAllHiddenTiles() {
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
        flipAllHiddenTiles();
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

    @Test
    public void pickFaceDown_remove_all_tiles() {
        flipAllHiddenTiles();
    }

    /* =====================
         Tests for faceUpCount()
       ========================= */
    @Test
    public void faceUpCount_all_tiles_visible() {
        flipAllHiddenTiles();
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
        flipAllHiddenTiles();
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
        assertTrue( deck.getAllFaceUp().get(0).isFaceUp());
    }
    
    @Test 
    public void putBack_is_in_face_up_list() {
        Tile tile_candidate = new Tile(2);
        deck.putBack(tile_candidate);
        assertEquals(tile_candidate, deck.getAllFaceUp().get(0));
    }
}
