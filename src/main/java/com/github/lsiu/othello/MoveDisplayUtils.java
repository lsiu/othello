package com.github.lsiu.othello;

import com.github.lsiu.othello.game.GameException;
import com.github.lsiu.othello.game.LocationStatus;
import com.github.lsiu.othello.game.OthelloBoard;

public class MoveDisplayUtils {

    public static String generateMoveOutput(String move, LocationStatus player, OthelloBoard board) {
        return String.format("Player '%s' move: %s\n%s", player, move, board.toString());
    }

    public static void throwWinningMessage(LocationStatus winner, int winnerCount, int loserCount, String move, LocationStatus turn, OthelloBoard board) {
        String moveOutput = generateMoveOutput(move, turn, board);
        throw new GameException(String.format("%s\n\n" +
                        "No further moves available\n" +
                        "Player '%s' wins ( %s vs %s )", moveOutput, winner,
                winnerCount, loserCount));
    }

    public static void throwExceptionOnInvalidMove(String move, LocationStatus turn) {
        throw new GameException(String.format("Player '%s' move: %s\nInvalid move. Please try again.", turn, move));
    }
}
