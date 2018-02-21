package com.github.lsiu.othello.game.move;

import com.github.lsiu.othello.game.BoardUtils;
import com.github.lsiu.othello.game.Location;
import com.github.lsiu.othello.game.LocationStatus;
import com.github.lsiu.othello.game.OthelloBoard;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

public class CanPlayMakeAMoveUtils {

    public static boolean canPlayerMakeAMove(LocationStatus player, OthelloBoard board) {
        for (int i = 0; i < OthelloBoard.HEIGHT; i++) {
            for (int j = 0; j < OthelloBoard.WIDTH; j++) {
                Location location = new Location(j, i);
                if (board.get(location) != LocationStatus.EMPTY) {
                    continue;
                }
                List<Direction> directionList = directionsPlayerCanMakeAMove(player, board, location);
                if (!directionList.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<Direction> directionsPlayerCanMakeAMove(LocationStatus player, OthelloBoard board, Location location) {
        int row = location.getRow();
        int col = location.getCol();

        List<Direction> directionList = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int posX = col + dx;
                int posY = row + dy;
                if (BoardUtils.isOutOfBounds(posX, posY) || (dx == 0 && dy == 0)) {
                    continue;
                }
                LocationStatus locationStatus = board.get(posX, posY);
                if (locationStatus == player.opposite()) {
                    boolean canFlipInThisDirection = LookForPlayerUtils.lookForPlayerColourInDirection(dx, dy, board, new Location(posX, posY), player);
                    if (canFlipInThisDirection) {
                        directionList.add(new Direction(dx, dy));
                    }
                }
            }
        }
        return directionList;
    }

    @Value
    static class Direction {
        private int dx;
        private int dy;
    }
}
