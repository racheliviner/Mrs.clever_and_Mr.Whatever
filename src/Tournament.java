public class Tournament {
    public static void main(String[] args) {
        Renderer renderBoard = new ConsoleRenderer();
        Player playerX = new HumanPlayer(), playerY = new HumanPlayer();
        Game game = new Game(playerX, playerY, renderBoard);
        GameStatus winner = game.play();
        if (winner == GameStatus.O_WIN)
            System.out.println("THE WINNER IS O!");
        if (winner == GameStatus.X_WIN)
            System.out.println("THE WINNER IS X!");
        if (winner == GameStatus.DRAW)
            System.out.println("X and O both win!");
    }
}
