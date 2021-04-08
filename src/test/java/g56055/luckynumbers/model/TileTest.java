package g56055.luckynumbers.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Tile class
 * 
 * @author Duran Rehan g56055
 */
public class TileTest {

    public TileTest() {
    }

        /* =====================
         Tests for faceFaceUp()
        ======================= */
    @Test
    public void faceFaceUp_when_is_not_visible() {
        Tile t1 = new Tile(2);
        assertFalse(t1.isFaceUp());
    }

    @Test
    public void faceFaceUp_when_is_already_visible() {
        Tile t1 = new Tile(2);
        t1.flipFaceUp();
        assertTrue(t1.isFaceUp());
    }

}
