package tiktactoe.player;

import tiktactoe.main.Board;
import tiktactoe.main.Mark;

public interface Player {
    void playTurn(Board board, Mark mark);
    String getName();
}
