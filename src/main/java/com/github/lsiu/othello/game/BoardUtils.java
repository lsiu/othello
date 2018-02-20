package com.github.lsiu.othello.game;

public class BoardUtils {
    public static boolean isOutOfBounds(int col, int row) {
        return row < 0 || row > 7 || col < 0 || col > 7;
    }
}
