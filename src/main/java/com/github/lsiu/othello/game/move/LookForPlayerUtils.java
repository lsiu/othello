package com.github.lsiu.othello.game.move;

import com.github.lsiu.othello.game.BoardUtils;
import com.github.lsiu.othello.game.Location;
import com.github.lsiu.othello.game.LocationStatus;
import com.github.lsiu.othello.game.OthelloBoard;

class LookForPlayerUtils {
    /*
    1 --------
    2 --------
    3 --------
    4 ----X---
    5 ---Y----
    6 --Z-----
    7 --------
    8 --------
      abcdefgh

      Given X is the position player put down their piece
      And y is the direction we are searching, i.e. in this example dx=-1, dy=-1
      input "location" = position "Y" in the diagram
      and position "Z" will be the first location we check
     */
    static boolean lookForPlayerColourInDirection(int dx, int dy, OthelloBoard board, Location location, LocationStatus player) {
        int posX = location.getCol() + dx;
        int posY = location.getRow() + dy;
        while (!BoardUtils.isOutOfBounds(posX, posY)) {
            LocationStatus locationStatus = board.get(posX, posY);

            if (locationStatus == player) {
                return true;
            } else if (locationStatus == LocationStatus.EMPTY) {
                return false;
            }

            posX = posX + dx;
            posY = posY + dy;
        }
        return false;
    }
}
