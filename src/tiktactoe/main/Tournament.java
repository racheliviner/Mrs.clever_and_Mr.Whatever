package tiktactoe.main;

import tiktactoe.player.Player;
import tiktactoe.player.PlayerFactory;
import tiktactoe.rendering.Renderer;
import tiktactoe.rendering.RendererFactory;

/**
 * The Tournament class represents a Tic-Tac-Toe tournament between two players.
 */
public class Tournament {
    private Player player1;
    private Player player2;
    private Renderer renderBoard;
    private int numOfRounds;

    /**
     * Constructor for the Tournament class.
     *
     * @param player1      The first player.
     * @param player2      The second player.
     * @param renderBoard  The renderer for displaying the game board.
     * @param numOfRounds  The number of rounds in the tournament.
     */
    public Tournament(Player player1, Player player2, Renderer renderBoard, int numOfRounds) {
        this.player1 = player1;
        this.player2 = player2;
        this.renderBoard = renderBoard;
        this.numOfRounds = numOfRounds;
    }

    /**
     * Plays a single round of the tournament.
     *
     * @param game      The game instance for the round.
     * @param playerX   The identifier for the player using 'X'.
     * @param playerO   The identifier for the player using 'O'.
     * @return The identifier of the winning player (1 or 2), or 0 for a draw.
     */
    private int playRound(Game game, int playerX, int playerO) {
        GameStatus winner = game.play();
        if (winner == GameStatus.X_WIN)
            return playerX;
        if (winner == GameStatus.O_WIN)
            return playerO;
        return 0;
    }

    /**
     * Plays the entire tournament with the specified number of rounds.
     *
     * @return An array representing the results: [player1 wins, player2 wins, draws].
     */
    public int[] playTournament() {
        int player1Win = 0;
        int player2Win = 0;
        int draw = 0;
        int winner;

        for (int i = 1; i <= this.numOfRounds; i++) {
            if (i % 2 != 0) {
                // Odd rounds: player1 as 'X', player2 as 'O'
                Game game = new Game(this.player1, this.player2, this.renderBoard);
                winner = playRound(game, 1, 2);
            } else {
                // Even rounds: player2 as 'X', player1 as 'O'
                Game game = new Game(this.player2, this.player1, this.renderBoard);
                winner = playRound(game, 2, 1);
            }

            // Update scores based on the winner
            if (winner == 1) {
                player1Win++;
            } else if (winner == 2) {
                player2Win++;
            } else {
                draw++;
            }
        }

        return new int[]{player1Win, player2Win, draw};
    }

    /**
     * Entry point for running the Tic-Tac-Toe tournament.
     *
     * @param args Command-line arguments specifying player types, renderer type, and number of rounds.
     */
    public static void main(String[] args) {
        String[] args = {"clever", "human", "console", "3"};
        String typeOfPlayer1 = args[0];
        String typeOfPlayer2 = args[1];
        String typeOfRenderer = args[2];
        int numOfRounds = Integer.parseInt(args[3]);

        if (numOfRounds < 1) {
            System.err.println("The number of rounds can't be negative");
            return;
        }

        // Build players and renderer based on user input
        PlayerFactory playerFactory = new PlayerFactory();
        Player player1 = playerFactory.buildPlayer(typeOfPlayer1);
        Player player2 = playerFactory.buildPlayer(typeOfPlayer2);

        if (player1 == null || player2 == null) {
            System.err.println("The player type is illegal, try again");
            return;
        }

        RendererFactory rendererFactory = new RendererFactory();
        Renderer renderBoard = rendererFactory.buildRenderer(typeOfRenderer);

        if (renderBoard == null) {
            System.err.println("The renderer type is illegal, try again");
            return;
        }

        // Run the tournament
        Tournament tournament = new Tournament(player1, player2, renderBoard, numOfRounds);
        int[] result = tournament.playTournament();

        // Display tournament results
        System.out.println("\nThe tournament result:\n\t" +
                player1.getName() + " (1) won: " + result[0] + " rounds\n\t" +
                player2.getName() + " (2) won: " + result[1] + " rounds\n\t" +
                "Draws: " + result[2] + " rounds\n");
    }
}
