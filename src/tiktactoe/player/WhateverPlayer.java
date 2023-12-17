package tiktactoe.player;

import tiktactoe.main.Board;
import tiktactoe.main.Mark;

import java.util.*;

public class WhateverPlayer implements Player {
    private Scanner scanner = new Scanner(System.in);
    private Random rand = new Random();
    private String name = "Mr. Whatever";

    public void playTurn(Board board, Mark mark) {
        while (true) {
            int row = rand.nextInt(board.SIZE);
            int col = rand.nextInt(board.SIZE);
            if (board.putMark(mark, row, col))
                return;
        }
    }
    public String getName(){
        return this.name;
    }
}
