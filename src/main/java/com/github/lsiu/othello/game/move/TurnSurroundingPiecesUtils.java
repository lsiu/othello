package com.github.lsiu.othello.game.move;

import com.github.lsiu.othello.game.BoardUtils;
import com.github.lsiu.othello.game.Location;
import com.github.lsiu.othello.game.LocationStatus;
import com.github.lsiu.othello.game.OthelloBoard;

public class TurnSurroundingPiecesUtils {

    public static boolean turnSurroundingPieces(OthelloBoard board, Location location, LocationStatus player) {
        int row = location.getRow();
        int col = location.getCol();

        boolean somePieceTurned = false;

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
                        somePieceTurned = true;
                        FlipOpponentUtils.flipOpponentInDirection(dx, dy, board, location, player);
                    }
                }
            }
        }
        return somePieceTurned;
    }

}
