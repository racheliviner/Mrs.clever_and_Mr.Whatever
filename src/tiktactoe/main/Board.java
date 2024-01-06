package tiktactoe.main;

/**
 * The Board class represents the game board for Tic-Tac-Toe.
 */
public class Board {
    public static final int SIZE = 3;
    public static final int WIN_STREAK = 3;

    private Mark[][] board;
    private GameStatus gameStatus;
    private int capacity;

    /**
     * Constructor for the Board class. Initializes the board and game status.
     */
    public Board() {
        this.board = new Mark[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++)
            for (int col = 0; col < SIZE; col++)
                board[row][col] = Mark.BLANK;

        this.gameStatus = GameStatus.IN_PROGRESS;
        this.capacity = 0;
    }

    /**
     * Helper method to change the game status based on the winning mark.
     *
     * @param mark The winning mark.
     */
    private void changeStatus(Mark mark) {
        if (mark == Mark.X)
            this.gameStatus = GameStatus.X_WIN;
        else
            this.gameStatus = GameStatus.O_WIN;
    }

    /**
     * Places a mark on the board at the specified position.
     *
     * @param mark The mark to be placed (X or O).
     * @param row  The row index.
     * @param col  The column index.
     * @return True if the mark is placed successfully, false otherwise.
     */
    public boolean putMark(Mark mark, int row, int col) {
        if (row < SIZE && col < SIZE)
            if (this.board[row][col] == Mark.BLANK) {
                this.board[row][col] = mark;
                this.capacity++;

                // Check for a win after placing the mark
                if (checkForWin(mark, row, col)) {
                    changeStatus(mark);
                    return true;
                }

                // Check for a draw
                if (this.capacity == SIZE * SIZE)
                    this.gameStatus = GameStatus.DRAW;

                return true;
            }
        return false;
    }

    /**
     * Retrieves the mark at the specified position on the board.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The mark at the specified position.
     */
    public Mark getMark(int row, int col) {
        return this.board[row][col];
    }

    /**
     * Retrieves the current capacity of the board.
     *
     * @return The current capacity.
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Retrieves the current game status.
     *
     * @return The current game status.
     */
    public GameStatus GameStatus() {
        return gameStatus;
    }

    /**
     * Checks for a win based on the last placed mark and its position.
     *
     * @param mark The last placed mark.
     * @param row  The row index of the last placed mark.
     * @param col  The column index of the last placed mark.
     * @return True if there is a win, false otherwise.
     */
    public boolean checkForWin(Mark mark, int row, int col) {
        if (this.capacity >= WIN_STREAK * 2 - 2) {
            if (checkLine(row, col, 1, 0, mark))
                return true;
            if (checkLine(row, col, 0, 1, mark))
                return true;
            if (checkLine(row, col, 1, 1, mark))
                return true;
            if (checkLine(row, col, -1, 1, mark))
                return true;
        }
        return false;
    }

    /**
     * Helper method to check for a win along a specific direction.
     *
     * @param i    The starting row index.
     * @param j    The starting column index.
     * @param i_m  The row direction.
     * @param j_m  The column direction.
     * @param mark The mark to check for.
     * @return True if there is a win in the specified direction, false otherwise.
     */
    private boolean checkLine(int i, int j, int i_m, int j_m, Mark mark) {
        int sum = 1;

        // Check in the positive direction
        for (int row = i + i_m, col = j + j_m; row < SIZE && col < SIZE && row >= 0 && col >= 0; row += i_m, col += j_m)
            if (this.board[row][col] == mark)
                sum++;
            else
                break;

        // Check in the negative direction
        for (int row = i - i_m, col = j - j_m; row < SIZE && col < SIZE && row >= 0 && col >= 0; row -= i_m, col -= j_m) {
            if (this.board[row][col] == mark)
                sum++;
            else
                break;
        }

        return sum == WIN_STREAK;
    }
}
