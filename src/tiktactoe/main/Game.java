package tiktactoe.main;

import tiktactoe.player.Player;
import tiktactoe.rendering.Renderer;

/**
 * The Game class represents a Tic-Tac-Toe game.
 */
public class Game {
    private Player playerX;
    private Player playerO;
    private Renderer renderBoard;
    private Board board;

    /**
     * Constructor for the Game class.
     *
     * @param playerX     The player representing 'X'.
     * @param playerO     The player representing 'O'.
     * @param renderBoard The renderer for displaying the game board.
     */
    public Game(Player playerX, Player playerO, Renderer renderBoard) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderBoard = renderBoard;
        this.board = new Board();
    }

    /**
     * Plays the Tic-Tac-Toe game until it reaches a conclusion.
     *
     * @return The final status of the game.
     */
    public GameStatus play() {
        while (this.board.GameStatus() == GameStatus.IN_PROGRESS) {
            // Player X's turn
            this.renderBoard.renderBoard(board);
            playerX.playTurn(board, Mark.X);

            // Check for game end after player X's turn
            if (this.board.GameStatus() == GameStatus.IN_PROGRESS) {
                // Player O's turn
                this.renderBoard.renderBoard(board);
                playerO.playTurn(board, Mark.O);
            } else {
                // Game has ended
                break;
            }
        }

        // Display the final board state
        this.renderBoard.renderBoard(board);

        // Return the final status of the game
        return board.GameStatus();
    }
}
