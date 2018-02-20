package com.github.lsiu.othello.game.move;

import com.github.lsiu.othello.game.LocationConverter;
import com.github.lsiu.othello.game.LocationStatus;
import com.github.lsiu.othello.game.OthelloBoard;
import com.github.lsiu.othello.game.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class TurnSurroundingPiecesUtilsTest {

    private final LocationConverter locationConverter = new LocationConverter();

    @Test
    public void shouldTurnPieceInHorizontalDirection() {
        OthelloBoard board = TestUtils.setupBoard("" +
                "1 --------\n" +
                "2 --------\n" +
                "3 ----O---\n" +
                "4 --XOO---\n" +
                "5 ---XO---\n" +
                "6 --------\n" +
                "7 --------\n" +
                "8 --------\n" +
                "  abcdefgh"
        );
        TurnSurroundingPiecesUtils.turnSurroundingPieces(board, locationConverter.convert("4f"), LocationStatus.fromDisplayString("X"));

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
    public void shouldTurnPieceInMultipleDirection() {
        OthelloBoard board = TestUtils.setupBoard("" +
                "1 -X--X--X\n" +
                "2 --O-O-O-\n" +
                "3 ---OOO--\n" +
                "4 XOOO-OOX\n" +
                "5 ---OOO--\n" +
                "6 --O-O-O-\n" +
                "7 -O--O--X\n" +
                "8 X---X---\n" +
                "  abcdefgh"
        );
        TurnSurroundingPiecesUtils.turnSurroundingPieces(board, locationConverter.convert("4e"), LocationStatus.fromDisplayString("X"));

        Assert.assertThat(board.toString(), CoreMatchers.equalTo("" +
                "1 -X--X--X\n" +
                "2 --X-X-X-\n" +
                "3 ---XXX--\n" +
                "4 XXXX-XXX\n" +
                "5 ---XXX--\n" +
                "6 --X-X-X-\n" +
                "7 -X--X--X\n" +
                "8 X---X---\n" +
                "  abcdefgh"));
    }

    @Test
    public void shouldNotTurnAnyPieces() {
        String initialBoard = "" +
                "1 --------\n" +
                "2 --------\n" +
                "3 ---X----\n" +
                "4 ---XX---\n" +
                "5 --OOX---\n" +
                "6 --------\n" +
                "7 --------\n" +
                "8 --------\n" +
                "  abcdefgh";
        OthelloBoard board = TestUtils.setupBoard(initialBoard);
        String move = "e7";
        Assert.assertFalse(
                String.format("expect no pieces turned when move at %s. Board before:\n%s\n. Board after:\n%s\n.", move, initialBoard, board.toString()),
                TurnSurroundingPiecesUtils.turnSurroundingPieces(board, locationConverter.convert(move), LocationStatus.fromDisplayString("X"))
        );

        Assert.assertThat(board.toString(), CoreMatchers.equalTo("" +
                "1 --------\n" +
                "2 --------\n" +
                "3 ---X----\n" +
                "4 ---XX---\n" +
                "5 --OOX---\n" +
                "6 --------\n" +
                "7 --------\n" +
                "8 --------\n" +
                "  abcdefgh"
        ));
    }
}