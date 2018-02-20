package com.github.lsiu.othello.game;

public class TestUtils {

    /**
     * 1 --------
     * 2 --------
     * 3 ----O---
     * 4 --XXO---
     * 5 ---XO---
     * 6 --------
     * 7 --------
     * 8 --------
     * abcdefgh
     */
    public static OthelloBoard setupBoard(String board) {
        OthelloBoard othelloBoard = new OthelloBoard();
        String[] rows = board.split("\n");

        if (rows.length != 9) {
            throw new IllegalArgumentException(String.format("Unexpected number of rows!\n%s", board));
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 10; j++) {
                int x = i;
                int y = j - 2;
                LocationStatus status = LocationStatus.fromDisplayString(Character.toString(rows[i].charAt(j)));
                othelloBoard.mark(new Location(y, x), status);
            }
        }

        return othelloBoard;
    }
}
