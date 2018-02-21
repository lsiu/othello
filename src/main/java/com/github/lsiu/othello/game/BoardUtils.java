package com.github.lsiu.othello.game;

public class BoardUtils {
    public static boolean isOutOfBounds(int col, int row) {
        return row < 0 || row >= OthelloBoard.HEIGHT || col < 0 || col >= OthelloBoard.WIDTH;
    }
}
