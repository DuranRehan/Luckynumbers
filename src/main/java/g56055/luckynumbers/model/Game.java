package g56055.luckynumbers.model;

import static g56055.luckynumbers.model.State.*;

/**
 *
 * @author Duran Rehan g56055
 *
 * Gather the necessary elements for the game and implement the different stages
 * of the game.
 */
public class Game implements Model {

    private State state;
    private int playerCount;
    private int currentPlayerNumber;
    private Board[] boards;
    private Tile pickedTile;

    /**
     * Define a game to its initial state
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
        this.playerCount = playerCount;
        this.boards = new Board[playerCount];
        for (int i = 0; i <= playerCount - 1; i++) {
            boards[i] = new Board();
        }
    }

    @Override
    public Tile pickTile() {
        if (state != PICK_TILE) {
            throw new IllegalStateException("Not PICK_TILE");
        }
        this.state = PLACE_TILE;
        int nombreAleatoire = 1 + (int) (Math.random() * (20 + 1));
        this.pickedTile = new Tile(nombreAleatoire);
        return this.pickedTile;
    }

    /**
     * Pick a tile with the given value. Should be used only for the JUnit
     * tests.
     *
     * @param value The value we want to place
     * @return the picked tile
     */
    Tile pickTile(int value) {
        if (state != PICK_TILE) {
            throw new IllegalStateException("Not PICK_TILE");
        }
        this.state = PLACE_TILE;
        this.pickedTile = new Tile(value);
        return this.pickedTile;
    }

    @Override
    public int getBoardSize() {

        return boards[currentPlayerNumber].getSize();
    }

    @Override
    public void putTile(Position pos) {
        if (this.state != PLACE_TILE) {
            throw new IllegalStateException("IS NOT PLACE_TILE STATE ");
        }
        if (!canTileBePut(pos)) {
            throw new IllegalArgumentException("Tile cannot be place !");
        }
        boards[currentPlayerNumber].put(pickedTile, pos);
        if (boards[currentPlayerNumber].isFull()) {
            this.state = GAME_OVER;
        } else {
            this.state = TURN_END;
        }
    }

    @Override
    public void nextPlayer() {
        if (state != TURN_END) {
            throw new IllegalStateException(" TURN is not END");
        }
        state = PICK_TILE;
        if (currentPlayerNumber >= playerCount - 1) {
            currentPlayerNumber = 0;
        } else {
            currentPlayerNumber = currentPlayerNumber + 1;
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
        if (state != PLACE_TILE) {
            throw new IllegalStateException("NOT PLACE_TILE");
        }
        return pickedTile;
    }

    @Override
    public boolean isInside(Position pos) {
        return boards[currentPlayerNumber].isInside(pos);
    }

    @Override
    public boolean canTileBePut(Position pos) {
        if (state != PLACE_TILE) {
            throw new IllegalStateException("IS NOT PLACE TILE");
        }
        if (boards[currentPlayerNumber].isInside(pos) == false) {
            throw new IllegalArgumentException("Position is outside the board");
        }

        return boards[currentPlayerNumber].canBePut(pickedTile, pos);
    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        if (state == NOT_STARTED) {
            throw new IllegalStateException("GAME IS NOT STARTED");
        }
        if (!isInside(pos) || playerNumber > playerCount || playerNumber < 0) {
            throw new IllegalArgumentException("Tile is not valide");
        }

        return boards[currentPlayerNumber].getTile(pos);
    }

    @Override
    public int getWinner() {
        if (state != GAME_OVER) {
            throw new IllegalStateException("GAME IS NOT OVER");
        }

        return currentPlayerNumber;
    }
}
