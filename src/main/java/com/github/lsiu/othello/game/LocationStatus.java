package com.github.lsiu.othello.game;

public enum LocationStatus {
    EMPTY("-"), BLACK("X"), WHITE("O");

    private final String display;

    LocationStatus(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return display;
    }
}
