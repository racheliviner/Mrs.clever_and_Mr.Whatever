package tiktactoe.player;

import tiktactoe.main.Board;
import tiktactoe.main.Mark;

import java.util.Scanner;

public class HumanPlayer implements Player {
    //    void playTurn (tiktactoe.main.Board board, tiktactoe.main.Mark mark)
    private Scanner scanner = new Scanner(System.in);
    private String name = "Human player";

    public void playTurn(Board board, Mark mark) {
        while (true) {
            System.out.format("Choose coordinates for %s: ", mark);
            int cord = scanner.nextInt();
            if (board.putMark(mark, cord / 10 - 1, cord % 10 - 1))
                return;
        }
    }
    public String getName(){
        return this.name;
    }
}
