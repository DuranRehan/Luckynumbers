package g56055.luckynumbers.view;

import g56055.luckynumbers.model.Model;
import g56055.luckynumbers.model.Position;
import g56055.luckynumbers.model.State;
import java.util.Scanner;

/**
 *
 * @author Duran Rehan g56055
 *
 * Gather the necessary elements for the game view and implement the console
 * view
 */
public class MyView implements View {

    private final Model game;

    public MyView(Model game) {
        this.game = game;
    }

    @Override
    public void displayWelcome() {
        System.out.println("====================================");
        System.out.println("#   Welcome to Lucky Numbers !     #");
        System.out.println("#   Write by Duran Rehan for DEV2  #");
        System.out.println("#           Version 0.1            #");
        System.out.println("====================================");
    }

    @Override
    public void displayGame() {
        int player = game.getCurrentPlayerNumber();
        System.out.println("Board of player " + player);
        displayUpperFrame();
        displayBodyBoard();
        displayBottomFrame();
    }

    /**
     * Display the upper frame of the game board
     */
    private void displayUpperFrame() {
        int sizeBoard = game.getBoardSize();
        for (int i = 1; i <= sizeBoard; i++) {
            System.out.print("     " + i);
        }
        System.out.println("");
        System.out.println("  #========================#");

    }

    /**
     * Display the Body of the game board
     */
    private void displayBodyBoard() {
        int sizeBoard = game.getBoardSize();
        for (int row = 0; row < sizeBoard; row++) {
            System.out.print(row + 1 + " |");
            for (int col = 0; col < sizeBoard; col++) {
                System.out.print("  " + GetTileInInt(new Position(row, col)));
                System.out.print("   ");
            }
            System.out.println("");
        }
    }

    /**
     * Get the tile of a certain position in the game board and transform it in
     * Integer
     *
     * @param pos position on the game board
     * @return the value of the tile at certain position
     */
    private int GetTileInInt(Position pos) {
        int player = game.getCurrentPlayerNumber();
        if (game.getTile(player, pos) == null) {
            return 0;
        }
        return game.getTile(player, pos).getValue();
    }

    /**
     * Display the bottom frame of the game board
     */
    private void displayBottomFrame() {
        System.out.println("  #========================#");
        if (game.getState() == State.PLACE_TILE) {
            System.out.println("Picked tile : "
                    + game.getPickedTile().getValue());
        }
    }

    @Override
    public void displayWinner() {
        System.out.println("#========================#");
        System.out.println("#                        ");
        System.out.println("#  And the winner is ?!  ");
        System.out.println("#                        ");
        System.out.println("#            " + game.getWinner());
        System.out.println("#  WellPlay !            ");
        System.out.println("#========================#");
    }

    @Override
    public void displayError(String message) {
        System.out.println("[ERROR] " + message);
    }

    @Override
    public int askPlayerCount() {
        System.out.println("Enter with how many players would you play : ");
        return reading_int_Robust();
    }

    @Override
    public Position askPosition() {
        System.out.println("[CHOOSE] Which row : ");
        int row = reading_int_Robust() - 1;
        System.out.println("[CHOOSE] Which column : ");
        int col = reading_int_Robust() - 1;
        Position pos = new Position(row, col);
        while (!game.isInside(pos)) {
            displayError("Position is not valide ! Retry : ");
            System.out.println("[CHOOSE] Which row : ");
            row = reading_int_Robust() - 1;
            System.out.println("[CHOOSE] Which column : ");
            col = reading_int_Robust() - 1;
            pos = new Position(row, col);
        }
        return new Position(row, col);
    }

    /**
     * Robustly read an integer entered on keyboard
     *
     * @return the integer entered on the keyboard
     */
    private int reading_int_Robust() {
        Scanner kbd = new Scanner(System.in);
        while (!kbd.hasNextInt()) {
            System.out.println("Number is not a Integer : ");
            kbd.next();
        }
        return kbd.nextInt();
    }
}