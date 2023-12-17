package tiktactoe.main;

import tiktactoe.player.Player;
import tiktactoe.rendering.Renderer;

public class Game {
    private Player playerX;
    private Player playerO;
    private Renderer renderBoard;
    private Board board;


    public Game(Player playerX, Player playerO, Renderer renderBoard) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderBoard = renderBoard;
        this.board = new Board();
    }

    public GameStatus play() {
        while (this.board.GameStatus() == GameStatus.IN_PROGRESS) {
            this.renderBoard.renderBoard(board);
            playerX.playTurn(board, Mark.X);
            if (this.board.GameStatus() == GameStatus.IN_PROGRESS){
                this.renderBoard.renderBoard(board);
                playerO.playTurn(board, Mark.O);
            }
            else
                break;
        }
        this.renderBoard.renderBoard(board);
        return board.GameStatus();
    }
}
