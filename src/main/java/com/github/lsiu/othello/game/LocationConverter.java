package com.github.lsiu.othello.game;

import org.springframework.core.convert.converter.Converter;

public class LocationConverter implements Converter<String, Location> {
    @Override
    public Location convert(String s) {
        int row = s.codePointAt(0) - ((int) '1');
        if (row >= 0 && row < OthelloBoard.HEIGHT) {
            int col = s.codePointAt(1) - ((int) 'a');
            if (col >= 0 && col < OthelloBoard.WIDTH) {
                return new Location(col, row);
            }
        }

        row = s.codePointAt(1) - ((int) '1');
        if (row >= 0 && row < OthelloBoard.HEIGHT) {
            int col = s.codePointAt(0) - ((int) 'a');
            if (col >= 0 && col < OthelloBoard.WIDTH) {
                return new Location(col, row);
            }
        }

        throw new IllegalArgumentException(String.format("unexpected location representation=%s", s));
    }
}
