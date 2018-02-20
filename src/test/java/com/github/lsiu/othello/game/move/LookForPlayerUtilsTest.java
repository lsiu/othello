package com.github.lsiu.othello.game.move;

import com.github.lsiu.othello.game.LocationConverter;
import com.github.lsiu.othello.game.LocationStatus;
import com.github.lsiu.othello.game.OthelloBoard;
import com.github.lsiu.othello.game.TestUtils;
import org.junit.Assert;
import org.junit.Test;

public class LookForPlayerUtilsTest {

    private final LocationConverter locationConverter = new LocationConverter();

    @Test
    public void shouldFindPlayerColourInDiagonalDirectionWith2OpponentPieceInBetween() {
        OthelloBoard board = TestUtils.setupBoard("" +
                "1 --X-----\n" +
                "2 ---O----\n" +
                "3 ----O---\n" +
                "4 --------\n" +
                "5 --------\n" +
                "6 --------\n" +
                "7 --------\n" +
                "8 --------\n" +
                "  abcdefgh"
        );

        Assert.assertTrue(LookForPlayerUtils.lookForPlayerColourInDirection(-1, -1, board, locationConverter.convert("4f"), LocationStatus.fromDisplayString("X")));
    }

}