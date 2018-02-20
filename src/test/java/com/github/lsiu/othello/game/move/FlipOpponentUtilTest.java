package com.github.lsiu.othello.game.move;

import com.github.lsiu.othello.game.LocationConverter;
import com.github.lsiu.othello.game.LocationStatus;
import com.github.lsiu.othello.game.OthelloBoard;
import com.github.lsiu.othello.game.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class FlipOpponentUtilTest {

    private final LocationConverter locationConverter = new LocationConverter();


    @Test
    public void shouldTurnPieceInHorizontalDirection() {
        OthelloBoard board = TestUtils.setupBoard("" +
                "1 --------\n" +
                "2 --------\n" +
                "3 ----O---\n" +
                "4 --XXO---\n" +
                "5 ---XO---\n" +
                "6 --------\n" +
                "7 --------\n" +
                "8 --------\n" +
                "  abcdefgh"
        );
        FlipOpponentUtils.flipOpponentInDirection(-1, 0, board, locationConverter.convert("4f"), LocationStatus.fromDisplayString("X"));

        Assert.assertThat(board.toString(), CoreMatchers.equalTo("" +
                "1 --------\n" +
                "2 --------\n" +
                "3 ----O---\n" +
                "4 --XXX---\n" +
                "5 ---XO---\n" +
                "6 --------\n" +
                "7 --------\n" +
                "8 --------\n" +
                "  abcdefgh"));
    }

    @Test
    public void shouldTurnPieceInDiagonalDirection() {
        OthelloBoard board = TestUtils.setupBoard("" +
                "1 --X-----\n" +
                "2 ---O----\n" +
                "3 ----O---\n" +
                "4 --XXO---\n" +
                "5 ---XO---\n" +
                "6 --------\n" +
                "7 --------\n" +
                "8 --------\n" +
                "  abcdefgh"
        );
        FlipOpponentUtils.flipOpponentInDirection(-1, -1, board, locationConverter.convert("4f"), LocationStatus.fromDisplayString("X"));

        Assert.assertThat(board.toString(), CoreMatchers.equalTo("" +
                "1 --X-----\n" +
                "2 ---X----\n" +
                "3 ----X---\n" +
                "4 --XXO---\n" +
                "5 ---XO---\n" +
                "6 --------\n" +
                "7 --------\n" +
                "8 --------\n" +
                "  abcdefgh"));
    }

}