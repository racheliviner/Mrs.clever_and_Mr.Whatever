enum Mark {BLANK, X, O}

enum GameStatus {DRAW, X_WIN, O_WIN, IN_PROGRESS}

public class Board {
    //    Board(){}
//    boolean putMark(Mark mark, int row, int col)
//    GameStatus GameStatus()
//    Mark getMark(int row, int col)
    public static final int SIZE = 3;
    public static final int WIN_STREAK = 3;
    private Mark[][] board;
    private GameStatus gameStatus;
    private int capacity;

    public Board() {
        this.board = new Mark[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++)
            for (int col = 0; col < SIZE; col++)
                board[row][col] = Mark.BLANK;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.capacity = 0;
    }

    private void changeStatus(Mark mark){
        if (mark == Mark.X)
            this.gameStatus = GameStatus.X_WIN;
        else
            this.gameStatus = GameStatus.O_WIN;
    }
    public boolean putMark(Mark mark, int row, int col) {
        if (row < SIZE && col < SIZE)
            if (this.board[row][col] == Mark.BLANK) {
                this.board[row][col] = mark;
                this.capacity++;
                if(checkForWin(mark,row,col)) {
                    changeStatus(mark);
                }
                if (this.capacity == SIZE * SIZE)
                    this.gameStatus = GameStatus.DRAW;
                return true;
            }
        return false;
    }

    public Mark getMark(int row, int col) {
        return this.board[row][col];
    }

    public int getCapacity(){
        return this.capacity;
    }

    public GameStatus GameStatus() {
        return gameStatus;
    }

    private boolean chekLine(int i, int j,int i_m, int j_m, Mark mark){
        int sum = 0;
        for (int row = i+i_m, col = j+j_m; row < SIZE && col < SIZE && row >=0 && col >=0; row+=i_m,col+=j_m)
            if (this.board[row][col] == mark)
                 sum++;
            else
                break;
        for (int row = i-i_m, col = j-j_m; row < SIZE && col < SIZE && row >=0 && col >=0 ; row-=i_m, col-=j_m){
            if (this.board[row][col] == mark)
                sum++;
            else
                break;
        }
        if (sum == WIN_STREAK-1)
            return true;
        else
            return false;
    }

    public boolean checkForWin(Mark mark, int row, int col) {
        if (this.capacity >= WIN_STREAK * 2 - 1) {
            if (chekLine(row,col,1,0,mark))
                return true;
            if(chekLine(row,col,0,1,mark))
                return true;
            if(chekLine(row,col,1,1,mark))
                return true;
            if(chekLine(row,col,-1,1,mark))
                return true;
        }
        return false;
    }
}
