package g56055.luckynumbers.model;

import static g56055.luckynumbers.model.State.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gather the necessary elements for the game and implement the different stages
 * of the game.
 *
 * @author Duran Rehan g56055
 */
public class Game implements Model {

    private State state;
    private int playerCount;
    private int currentPlayerNumber;
    private Board[] boards;
    private Tile pickedTile;
    private Deck deck;

    /**
     * Define a game to its initial state, in other words, the game has not yet
     * started
     */
    public Game() {
        this.state = NOT_STARTED;
    }

    @Override
    public void start(int playerCount) {
        if (state != NOT_STARTED && state != GAME_OVER) {
            throw new IllegalStateException("NOT NOT_STARTED OR NOT GAME_OVER");
        }
        if (playerCount < 2 || playerCount > 4) {
            throw new IllegalArgumentException("TO MUCH PLAYER OR NOT ENOUGH");
        }
        state = PICK_TILE;
        this.currentPlayerNumber = 0;
        this.deck = new Deck(playerCount);
        this.playerCount = playerCount;
        this.boards = new Board[playerCount];
        for (int i = 0; i <= playerCount - 1; i++) {
            boards[i] = new Board();
        }
        placeTileBeginning();
    }

//    @Override
//    public Tile pickTile() {
//        if (state != PICK_TILE) {
//            throw new IllegalStateException("Not PICK_TILE");
//        }
//        this.state = PLACE_TILE;
//        int rdm = JavaUtils.rdmNumber(1, 20);
//        this.pickedTile = new Tile(rdm);
//        return this.pickedTile;
//    }
//     
//    /**
//     * Pick a tile with the given value. Should be used only for the JUnit
//     * tests.
//     *
//     * @param value The value we want to place
//     * @return The tile with the chosen value
//     */
//    Tile pickTile(int value) {
//        if (state != PICK_TILE) {
//            throw new IllegalStateException("Not PICK_TILE");
//        }
//        this.state = PLACE_TILE;
//        this.pickedTile = new Tile(value);
//        return this.pickedTile;
//    }
    @Override
    public int getBoardSize() {
        return boards[currentPlayerNumber].getSize();
    }

    @Override
    public void putTile(Position pos) {
        if (this.state == PLACE_TILE
                || this.state == State.PLACE_OR_DROP_TILE) {
            if (!canTileBePut(pos)) {
                throw new IllegalArgumentException("Tile cannot be place !");
            }
            boards[currentPlayerNumber].put(pickedTile, pos);
            if (deck.faceDownCount() == 0) {
                this.state = GAME_OVER;
            } else if (boards[currentPlayerNumber].isFull()) {
                this.state = GAME_OVER;
            } else {
                this.state = TURN_END;
            }
        } else {
            throw new IllegalStateException("Is not the right State : "
                    + getState());
        }
    }

    @Override
    public void nextPlayer() {
        if (state != TURN_END) {
            throw new IllegalStateException("TURN is not END");
        }
        state = PICK_TILE;
        if (currentPlayerNumber == playerCount - 1) {
            currentPlayerNumber = 0;
        } else {
            currentPlayerNumber++;
        }
    }

    @Override
    public int getPlayerCount() {
        if (state == NOT_STARTED) {
            throw new IllegalStateException("GAME NOT STARTED");
        }
        return this.playerCount;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public int getCurrentPlayerNumber() {
        if (state == NOT_STARTED || state == GAME_OVER) {
            throw new IllegalStateException("GAME NOT STARTED OR IS OVER");
        }
        return currentPlayerNumber;
    }

    @Override
    public Tile getPickedTile() {
        if (this.state == PLACE_TILE
                || this.state == State.PLACE_OR_DROP_TILE) {
            return pickedTile;
        } else {
            throw new IllegalStateException("is not PLACE TILE OR "
                    + "PLACE_OR_DROP_TILE");
        }
    }

    @Override
    public boolean isInside(Position pos) {
        return boards[currentPlayerNumber].isInside(pos);
    }

    @Override
    public boolean canTileBePut(Position pos) {
        if (state == PLACE_TILE || state == PLACE_OR_DROP_TILE) {
            if (!boards[currentPlayerNumber].isInside(pos)) {
                throw new IllegalArgumentException("Position is outside "
                        + "the board");
            }
            return boards[currentPlayerNumber].canBePut(pickedTile, pos);
        } else {
            throw new IllegalStateException("is not PLACE TILE OR "
                    + "PLACE_OR_DROP_TILE");
        }
    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        if (state == NOT_STARTED) {
            throw new IllegalStateException("GAME IS NOT STARTED");
        }
        if (!isInside(pos) || playerNumber > playerCount || playerNumber < 0) {
            throw new IllegalArgumentException("the described tile "
                    + "is not valid");
        }
        return boards[currentPlayerNumber].getTile(pos);
    }

    @Override
    public List<Integer> getWinners() {
        if (state != GAME_OVER) {
            throw new IllegalStateException("GAME IS NOT OVER");
        }
        return getListOfWinners();
    }

    /**
     * Find all the winners of the game by counting the number of empty cases in
     * their boards
     *
     * @return list with all winners
     * @throws IllegalStateException if state is not GAME_OVER
     */
    private List<Integer> getListOfWinners() {
        List<Integer> winnersList = new ArrayList();
        int playerNumber = 0;
        int saveLastCount = boards[playerNumber].countEmptyCases();
        for (Board board : boards) {
            if (board.countEmptyCases() == saveLastCount) {
                winnersList.add(playerNumber);
            } else if (board.countEmptyCases() < saveLastCount) {
                saveLastCount = board.countEmptyCases();
                winnersList.clear();
                winnersList.add(playerNumber);
            }
            playerNumber++;
        }
        return winnersList;
    }

    @Override
    public Tile pickFaceDownTile() {
        if (state != PICK_TILE) {
            throw new IllegalStateException("Is not PICK_TILE !" + getState());
        }
        if (deck.faceDownCount() < 0) {
            throw new IllegalArgumentException("FaceDown Count < 0 ! ");
        }
        state = PLACE_OR_DROP_TILE;
        pickedTile = deck.pickFaceDown();
        return pickedTile;
    }

    void pickChosenFaceDownTile(int value) {
        if (state != PICK_TILE) {
            throw new IllegalStateException("Is not PICK_TILE !" + getState());
        }
        if (deck.faceDownCount() < 0) {
            throw new IllegalArgumentException("FaceDown Count < 0 ! ");
        }
        state = PLACE_OR_DROP_TILE;
        pickedTile = new Tile(value);
    }

    @Override
    public Tile pickFaceUpTile(Tile tile) {
        if (state != PICK_TILE) {
            throw new IllegalStateException("Is not PICK_TILE !" + getState());
        }
        state = PLACE_TILE;
        deck.pickFaceUp(tile);
        pickedTile = tile;
        return pickedTile;
    }

    @Override
    public void dropTile() {
        if (state != PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("Is not PLACE_OR_DROP_TILE !"
                    + getState());
        }
        state = TURN_END;
        deck.putBack(pickedTile);
    }

    @Override
    public int faceDownTileCount() {
        if (state == NOT_STARTED) {
            throw new IllegalStateException("Game : " + getState());
        }
        return deck.faceDownCount();
    }

    @Override
    public int faceUpTileCount() {
        if (state == NOT_STARTED) {
            throw new IllegalStateException("Game : " + getState());
        }
        return deck.faceUpCount();
    }

    @Override
    public List<Tile> getAllfaceUpTiles() {
        if (state == NOT_STARTED) {
            throw new IllegalStateException("Game : " + getState());
        }
        return Collections.unmodifiableList(deck.getAllFaceUp());
    }

    @Override
    public void placeTileBeginning() {
        if (state != PICK_TILE) {
            throw new IllegalStateException("State is not PICK_TILE : "
                    + getState());
        }
        for (Board board : boards) {
            List<Tile> tiles = deck.pickRandomDownTile(getBoardSize());
            int posCounter = 0;
            for (Tile tile : tiles) {
                board.put(tile, new Position(posCounter, posCounter));
                posCounter++;
            }
        }
    }
}
