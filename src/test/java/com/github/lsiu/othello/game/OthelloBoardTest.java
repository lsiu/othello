package com.github.lsiu.othello.game;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class OthelloBoardTest {

    private final LocationConverter locationConverter = new LocationConverter();

    @Test
    public void testGetLocation() {
        OthelloBoard othelloBoard = TestUtils.setupBoard("" +
                "1 --------\n" +
                "2 ------X-\n" +
                "3 --------\n" +
                "4 --------\n" +
                "5 --------\n" +
                "6 --------\n" +
                "7 --------\n" +
                "8 --------\n" +
                "  abcdefgh");

        assertThat(othelloBoard.get(locationConverter.convert("2g")), CoreMatchers.equalTo(LocationStatus.fromDisplayString("X")));
        assertThat(othelloBoard.get(6, 1), CoreMatchers.equalTo(LocationStatus.fromDisplayString("X")));
    }

}