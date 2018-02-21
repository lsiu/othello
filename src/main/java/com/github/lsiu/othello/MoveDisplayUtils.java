package com.github.lsiu.othello;

import com.github.lsiu.othello.game.LocationStatus;
import com.github.lsiu.othello.game.OthelloBoard;

public class MoveDisplayUtils {

    public static String generateMoveOutput(String move, LocationStatus player, OthelloBoard board) {
        return String.format("Player '%s' move: %s\n%s", player, move, board.toString());
    }
}
