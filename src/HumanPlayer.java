import java.util.Scanner;

public class HumanPlayer implements Player{
    //    void playTurn (Board board, Mark mark)
    private Scanner in = new Scanner(System.in);

    public void playTurn(Board board, Mark mark) {
        while (true) {
            System.out.println( mark + " player coordinates: ");
            int cord = in.nextInt();
            if (board.putMark(mark, cord / 10 - 1, cord % 10 - 1))
                return;
        }
    }
}
