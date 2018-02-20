package com.github.lsiu.othello.game;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class TestUtilsTest {

    @Test
    public void testSetupNewBoard() {
        String board = "" +
                "1 --------\n" +
                "2 --------\n" +
                "3 --------\n" +
                "4 ---OX---\n" +
                "5 ---XO---\n" +
                "6 --------\n" +
                "7 --------\n" +
                "8 --------\n" +
                "  abcdefgh";

        OthelloBoard othelloBoard = TestUtils.setupBoard(board);

        assertThat(othelloBoard.toString(), CoreMatchers.equalTo(board));
    }

    @Test
    public void testSetupModifiedBoard() {
        String board = "" +
                "1 --------\n" +
                "2 --------\n" +
                "3 ----O---\n" +
                "4 --XXO---\n" +
                "5 ---XO---\n" +
                "6 --------\n" +
                "7 --------\n" +
                "8 --------\n" +
                "  abcdefgh";

        OthelloBoard othelloBoard = TestUtils.setupBoard(board);

        assertThat(othelloBoard.toString(), CoreMatchers.equalTo(board));
    }

}