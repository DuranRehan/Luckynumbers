package g56055.luckynumbers.model;

import java.util.ArrayList;
import java.util.List;
import g56055.luckynumbers.utils.JavaUtils;

/**
 * This class contains all the tiles available in the center of the "table"
 *
 * @author Duran Rehan g56055
 */
public class Deck {

    private List<Tile> faceUpTiles = new ArrayList<>();
    private List<Tile> faceDownTiles = new ArrayList<>();

    /**
     * Create a random deck of 20 tiles numbered from 1 to 20, 
     * as many times as there are players
     * 
     * @param playerCount number of players for whom a deck is needed
     */
    public Deck(int playerCount) {
        int i = 0;
        while (i < playerCount) {
            for (int x = 1; x <= 20; x++) {
                faceDownTiles.add(new Tile(x));
            }
            i++;
        }
        JavaUtils.shuffle(faceDownTiles);
    }

    /**
     * get the count of hidden tiles in the deck
     * 
     * @return count of hidden tiles
     */
    public int faceDownCount() {
        return faceDownTiles.size();
    }

    /**
     * remove a face down tile in the deck
     * 
     * @return the removed tile
     */
    public Tile pickFaceDown() {
        Tile tempTile = faceDownTiles.get(0);
        faceUpTiles.add(tempTile);
        faceDownTiles.remove(tempTile);
        return tempTile;
    }

    /**
     * get the count of visible tiles in the deck
     * 
     * @return the count of visible tiles
     */
    public int faceUpCount() {
        return faceUpTiles.size();
    }
    
    /**
     * Get all visible tiles in the deck
     * 
     * @return the entire list of visibles tiles
     */
    public List<Tile> getAllFaceUp() {
        return faceUpTiles;
    }

    /**
     * checks if the tile exists and is visible in the deck
     * 
     * @param tile the tile to check
     * @return true if the tile is visible and exists, false otherwise
     */
    public boolean hasFaceUp(Tile tile) {
        return tile.isFaceUp() && faceUpTiles.contains(tile);
    }

    /**
     * Removes a certain tile from the visible deck
     * 
     * @param tile the tile to remove in the deck
     */
    public void pickFaceUp(Tile tile) {
        faceUpTiles.remove(tile);
    }

    /**
     * Put a certain tile back in the deck with its face up
     *
     * @param tile the tile to put back in the deck
     */
    public void putBack(Tile tile) {
        tile.flipFaceUp();
        faceUpTiles.add(tile);
    }
    
    /**
     * This method aim to test the implemented method,
     * Should be used only for the JUnit tests.
     * 
     * @return the list of hidden tiles
     */
    List<Tile> getFaceDownTiles() {
        return faceDownTiles;
    }
}
