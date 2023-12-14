public class Tournament {
    private Player player1;
    private Player player2;
    Renderer renderBoard;
    int numOfRounds;
    public Tournament(Player player1, Player player2 , Renderer renderBoard, int numOfRounds){
        this.player1 = player1;
        this.player2 = player2;
        this.renderBoard = renderBoard;
        this.numOfRounds = numOfRounds;
    }
    private int playRound (Game game, int playerX, int playerO){
        GameStatus winner = game.play();
        if (winner == GameStatus.X_WIN)
            return playerX;
        if (winner == GameStatus.O_WIN)
            return playerO;
        return 0;
    }
    public int[] playTournament(){
        int player1win = 0;
        int player2win = 0;
        int winner;
        for (int i = 1; i<=this.numOfRounds; i++){
            if (i%2 != 0){
                Game game = new Game(this.player1, this.player2, this.renderBoard);
                winner = playRound(game, 1, 2);
            }
            else{
                Game game = new Game(this.player2, this.player1, this.renderBoard);
                winner = playRound(game, 2, 1);
            }
//            System.out.print("THE WINNER IS: ");
            if (winner == 1){
//                System.out.println(player1.getName()+" (1)");
                player1win++;
            }
            if (winner == 2){
//                System.out.println(player2.getName()+" (2)");
                player2win++;
            }
//            if (winner == 0)
//                System.out.println("Player 1 and 2 both win!");
        }
        return new int[] {player1win,player2win,(this.numOfRounds-player1win-player2win)};
    }
    public static void main(String[] args) {
        if (args.length!=4){
            System.err.println("you need to choose for 2 kinds of players, type of renderer and number of rounds");
            return;
        }
//        String[] args = {"clever", "human", "console", "3"} ;
        String typeOfPlayer1 = args[0];
        String typeOfPlayer2 = args[1];
        String typeOfRenderer = args[2];
        int numOfRounds =Integer.parseInt(args[3]);

        if (numOfRounds<1){
            System.err.println("The number of rounds cant be negative");
            return;
        }

        PlayerFactory playerFactory = new PlayerFactory();
        Player player1 = playerFactory.buildPlayer(typeOfPlayer1);
        Player player2 = playerFactory.buildPlayer(typeOfPlayer2);
        if (player1== null || player2== null){
        System.err.println("The player type is illegal, try again");
        return;
        }

        RendererFactory rendererFactory = new RendererFactory();
        Renderer renderBoard = rendererFactory.buildRenderer(typeOfRenderer);
        if (renderBoard == null){
            System.err.println("The renderer type is illegal, try again");
            return;
        }

        Tournament tournament = new Tournament(player1,player2,renderBoard,numOfRounds);
        int[] result = tournament.playTournament();
        System.out.println("\nThe tournament result:\n\t"+
                player1.getName() + " (1) wan: "+ result[0]  + " rounds\n\t" +
                player2.getName() + " (2) wan: "+result[1] +" rounds\n\t" +
                "A draw win: "+ result[2]  + " rounds\n");
    }
}
