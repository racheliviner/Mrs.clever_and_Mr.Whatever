package tiktactoe.player;

import tiktactoe.main.Board;
import tiktactoe.main.Mark;

import java.util.Random;

/**
 * The WhateverPlayer class represents a player with a random move strategy in Tic-Tac-Toe.
 */
public class WhateverPlayer implements Player {
    private Random rand = new Random();
    private String name = "Mr. Whatever";

    /**
     * Plays a turn for the WhateverPlayer by making random moves on the game board.
     *
     * @param board The game board.
     * @param mark  The player's mark.
     */
    public void playTurn(Board board, Mark mark) {
        while (true) {
            int row = rand.nextInt(board.SIZE);
            int col = rand.nextInt(board.SIZE);

            // Attempt to place the mark randomly on the board
            if (board.putMark(mark, row, col)) {
                return;
            }
        }
    }

    /**
     * Gets the name of the WhateverPlayer.
     *
     * @return The player's name.
     */
    public String getName() {
        return this.name;
    }
}
