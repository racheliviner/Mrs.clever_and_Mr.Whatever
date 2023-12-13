import java.util.*;

public class CleverPlayer implements Player {
    private Scanner scanner = new Scanner(System.in);
    private Random rand = new Random();
    private String name = "Mrs. clever";


    private boolean failTheEnemy(Board board, Mark mark) {
        for (int row = 0; row < board.SIZE; row++)
            for (int col = 0; col < board.SIZE; col++) {
                if (mark == Mark.X)
                    if (board.checkForWin(Mark.O, row, col))
                        if (board.putMark(mark, row, col))
                            return true;
                        else if (mark == Mark.O)
                            if (board.checkForWin(Mark.X, row, col))
                                if (board.putMark(mark, row, col))
                                    return true;
            }
        return false;
    }

    private boolean tryToWin(Board board, Mark mark) {
        for (int row = 0; row < board.SIZE; row++)
            for (int col = 0; col < board.SIZE; col++) {
                if (board.checkForWin(mark, row, col))
                    if (board.putMark(mark, row, col))
                        return true;
            }
        return false;
    }

    private boolean putNextToMark(Board board, Mark mark){
        for (int trys = 1; trys <= 10000; trys++) {
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

    public void playTurn(Board board, Mark mark) {
        if (board.getCapacity() >= 2) {
            if (tryToWin(board, mark))
                return;
            if (failTheEnemy(board, mark))
                return;
            if (putNextToMark(board, mark))
                return;
        }
        while (true) {
            int row = rand.nextInt(board.SIZE);
            int col = rand.nextInt(board.SIZE);
            if (board.putMark(mark, row, col))
                return;
        }
    }

    public String getName() {
        return this.name;
    }
}
