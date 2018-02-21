package com.github.lsiu.othello.game.move;

import com.github.lsiu.othello.game.Location;
import com.github.lsiu.othello.game.LocationStatus;
import com.github.lsiu.othello.game.OthelloBoard;

import java.util.List;

public class TurnSurroundingPiecesUtils {

    public static boolean turnSurroundingPieces(OthelloBoard board, Location location, LocationStatus player) {
        List<CanPlayMakeAMoveUtils.Direction> directionList = CanPlayMakeAMoveUtils.directionsPlayerCanMakeAMove(player, board, location);
        for (CanPlayMakeAMoveUtils.Direction direction : directionList) {
            FlipOpponentUtils.flipOpponentInDirection(direction.getDx(), direction.getDy(), board, location, player);
        }
        return !directionList.isEmpty();
    }
}
