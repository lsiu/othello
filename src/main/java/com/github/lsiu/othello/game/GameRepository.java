package com.github.lsiu.othello.game;

import org.springframework.stereotype.Component;

@Component
public class GameRepository {

    private OthelloGame othelloGame;

    public OthelloGame getTheOnlyGame() {
        return othelloGame;
    }

    public OthelloGame newGame() {
        othelloGame = new OthelloGame();
        return othelloGame;
    }

    public void setCurrentGameTo(OthelloGame othelloGame) {
        this.othelloGame = othelloGame;
    }
}
