package tiktactoe.player;

import tiktactoe.main.Board;
import tiktactoe.main.Mark;

import java.util.Scanner;

/**
 * The HumanPlayer class represents a human player in Tic-Tac-Toe.
 */
public class HumanPlayer implements Player {
    private Scanner scanner = new Scanner(System.in);
    private String name = "Human Player";

    /**
     * Plays a turn for the HumanPlayer by accepting user input for the move coordinates.
     *
     * @param board The game board.
     * @param mark  The player's mark.
     */
    public void playTurn(Board board, Mark mark) {
        while (true) {
            System.out.format("Choose coordinates for %s (row, col): ", mark);
            int coordinates = scanner.nextInt();

            // Extract row and column from the user input
            int row = coordinates / 10 - 1;
            int col = coordinates % 10 - 1;

            // Attempt to place the mark on the board
            if (board.putMark(mark, row, col)) {
                return;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    /**
     * Gets the name of the HumanPlayer.
     *
     * @return The player's name.
     */
    public String getName() {
        return this.name;
    }
}
