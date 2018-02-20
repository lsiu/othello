package com.github.lsiu.othello.game.move;

import com.github.lsiu.othello.game.BoardUtils;
import com.github.lsiu.othello.game.Location;
import com.github.lsiu.othello.game.LocationStatus;
import com.github.lsiu.othello.game.OthelloBoard;
import org.springframework.util.Assert;

class FlipOpponentUtils {

    static void flipOpponentInDirection(int dx, int dy, OthelloBoard board, Location location, LocationStatus player) {
        Assert.isTrue(!BoardUtils.isOutOfBounds(location.getCol(), location.getRow()), String.format("Expect %s to be inside the board range", location.toString()));

        int posX = location.getCol() + dx;
        int posY = location.getRow() + dy;

        LocationStatus newLocationStatus = board.get(posX, posY);
        if (newLocationStatus == LocationStatus.EMPTY) {
            throw new IllegalStateException(String.format("We should never be flipping opponent piece=%s in this direction (dx=%s, dy=%s) from %s",
                    player.opposite(), dx, dy, location.toString()));
        } else if (newLocationStatus == player.opposite()) {
            Location newLocation = new Location(posX, posY);
            board.mark(newLocation, player);
            flipOpponentInDirection(dx, dy, board, newLocation, player);
        } else {
            Assert.isTrue(newLocationStatus == player, String.format("unexpected status=%s at %s", newLocationStatus, new Location(posX, posY)));
        }
    }
}
