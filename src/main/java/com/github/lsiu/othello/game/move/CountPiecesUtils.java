package com.github.lsiu.othello.game.move;

import com.github.lsiu.othello.game.LocationStatus;
import com.github.lsiu.othello.game.OthelloBoard;

public class CountPiecesUtils {

    public static int count(OthelloBoard board, LocationStatus player) {
        int count = 0;
        for (int i = 0; i < OthelloBoard.HEIGHT; i++) {
            for (int j = 0; j < OthelloBoard.WIDTH; j++) {
                if (board.get(j, i) == player) {
                    count++;
                }
            }
        }
        return count;
    }
}
