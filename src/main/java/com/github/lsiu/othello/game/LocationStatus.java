package com.github.lsiu.othello.game;

import java.util.Arrays;

public enum LocationStatus {
    EMPTY("-"), BLACK("X"), WHITE("O");

    private final String display;

    LocationStatus(String display) {
        this.display = display;
    }

    public static LocationStatus fromDisplayString(String s) {
        return Arrays.stream(values())
                .filter(it -> it.display.equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("'%s' is not a valid string representation of %s", s, LocationStatus.class.getName())));
    }

    @Override
    public String toString() {
        return display;
    }

}
