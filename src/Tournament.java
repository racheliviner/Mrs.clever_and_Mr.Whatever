public class Tournament {
    private int playRound (Game game, int playerX, int playerY){
        System.out.println("In this round player "+ playerX +  " is X and player "+playerY+" is O:");
        GameStatus winner = game.play();
        System.out.print("THE WINNER IS: ");
        if (winner == GameStatus.X_WIN)
            return playerX;
        if (winner == GameStatus.O_WIN)
            return playerY;
        return 0;
    }
    public void playTournament(Player player1, Player player2 , Renderer renderBoard, int numOfRounds){
        int player1win = 0;
        int player2win = 0;
        int winner;
        for (int i = 1; i<=numOfRounds; i++){
            if (i%2 != 0){
                Game game = new Game(player1, player2, renderBoard);
                winner = playRound(game, 1, 2);

            }
            else{
                Game game = new Game(player2, player1, renderBoard);
                winner = playRound(game, 2, 1);
            }
            if (winner == 1){
                System.out.println("Player 1");
                player1win++;
            }
            if (winner == 2){
                System.out.println("Player 2");
                player2win++;
            }
            if (winner == 0)
                System.out.println("Player 1 and 2 both win!");
        }
        System.out.println("The tournament result:\n"+
                "   Player 1 win: "+ player1win  + " rounds\n" +
                "   Player 2 win: "+player2win+" rounds\n" +
                "   A draw win: "+ (numOfRounds-player1win-player2win) + " rounds");
    }
    public static void main(String[] args) {
        PlayerFactory playerFactory = new PlayerFactory();
        String type = "human";
        Player player1 = playerFactory.buildPlayer(type);
        Player player2 = playerFactory.buildPlayer(type);
        while (player1== null && player2== null){
            System.err.println("the player type is illegal");
            player1 = playerFactory.buildPlayer(type);
            player2 = playerFactory.buildPlayer(type);
        }

        RendererFactory rendererFactory = new RendererFactory();
        type = "console";
        Renderer renderBoard = rendererFactory.buildRenderer(type);
        while (renderBoard == null){
            System.err.println("the renderer type is illegal");
            renderBoard = rendererFactory.buildRenderer(type);
        }

        int numOfRounds = 2;
        Tournament tournament = new Tournament();
        tournament.playTournament(player1,player2,renderBoard,numOfRounds);

    }
}
