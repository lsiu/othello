package com.github.lsiu.othello.game;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertThat;

public class LocationConverterTest {

    private final LocationConverter converter = new LocationConverter();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testTopLeft1A() {
        Location location = converter.convert("1a");
        assertThat(location.getRow(), CoreMatchers.equalTo(0));
        assertThat(location.getCol(), CoreMatchers.equalTo(0));
    }

    @Test
    public void testBottomRight8h() {
        Location location = converter.convert("8h");
        assertThat(location.getRow(), CoreMatchers.equalTo(7));
        assertThat(location.getCol(), CoreMatchers.equalTo(7));
    }


    @Test
    public void testInvalidEntry() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(CoreMatchers.equalTo("unexpected location representation=!h"));
        converter.convert("!h");
    }
}