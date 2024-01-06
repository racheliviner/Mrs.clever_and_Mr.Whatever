package tiktactoe.player;

import tiktactoe.main.Board;
import tiktactoe.main.Mark;

import java.util.Random;

/**
 * The CleverPlayer class represents a computer player with a simple strategy in Tic-Tac-Toe.
 */
public class CleverPlayer implements Player {
    private Random rand = new Random();
    private String name = "Mrs. Clever";

    /**
     * Tries to place a mark to win the game.
     *
     * @param board The game board.
     * @param mark  The player's mark.
     * @return True if a winning move is made, false otherwise.
     */
    private boolean tryToWin(Board board, Mark mark) {
        for (int row = 0; row < board.SIZE; row++)
            for (int col = 0; col < board.SIZE; col++) {
                if (board.getMark(row, col) == Mark.BLANK)
                    if (board.checkForWin(mark, row, col))
                        if (board.putMark(mark, row, col))
                            return true;
            }
        return false;
    }

    /**
     * Tries to block the opponent from winning.
     *
     * @param board The game board.
     * @param mark  The player's mark.
     * @return True if a blocking move is made, false otherwise.
     */
    private boolean failTheEnemy(Board board, Mark mark) {
        System.out.println("I am trying to beat you");
        for (int row = 0; row < board.SIZE; row++)
            for (int col = 0; col < board.SIZE; col++) {
                if (board.getMark(row, col) == Mark.BLANK) {
                    if (mark == Mark.X) {
                        if (board.checkForWin(Mark.O, row, col))
                            if (board.putMark(mark, row, col))
                                return true;
                    } else if (mark == Mark.O) {
                        if (board.checkForWin(Mark.X, row, col))
                            if (board.putMark(mark, row, col))
                                return true;
                    }
                }
            }
        return false;
    }

    /**
     * Tries to place a mark next to the opponent's mark.
     *
     * @param board The game board.
     * @param mark  The player's mark.
     * @return True if a move is made next to the opponent's mark, false otherwise.
     */
    private boolean putNextToMark(Board board, Mark mark) {
        for (int trys = 1; trys <= 1000; trys++) {
            int row = rand.nextInt(board.SIZE);
            int col = rand.nextInt(board.SIZE);
            if (board.getMark(row, col) == Mark.BLANK) {
                if (row - 1 >= 0 && board.getMark(row - 1, col) == mark && board.putMark(mark, row, col))
                    return true;
                if (row + 1 < board.SIZE && board.getMark(row + 1, col) == mark && board.putMark(mark, row, col))
                    return true;
                if (col - 1 >= 0 && board.getMark(row, col - 1) == mark && board.putMark(mark, row, col))
                    return true;
                if (col + 1 < board.SIZE && board.getMark(row, col + 1) == mark && board.putMark(mark, row, col))
                    return true;
            }
        }
        return false;
    }

    /**
     * Plays a turn for the CleverPlayer.
     *
     * @param board The game board.
     * @param mark  The player's mark.
     */
    public void playTurn(Board board, Mark mark) {
        if (board.getCapacity() >= 2) {
            if (tryToWin(board, mark)) {
                System.out.println("I win");
                return;
            }
            if (failTheEnemy(board, mark)) {
                System.out.println("I beat you");
                return;
            }
            if (putNextToMark(board, mark)) {
                System.out.println("I put next to mark");
                return;
            }
        }
        // If no specific strategy is applied, place a mark randomly on the board.
        while (true) {
            int row = rand.nextInt(board.SIZE);
            int col = rand.nextInt(board.SIZE);
            if (board.putMark(mark, row, col))
                return;
        }
    }

    /**
     * Gets the name of the CleverPlayer.
     *
     * @return The player's name.
     */
    public String getName() {
        return this.name;
    }
}
