package com.github.lsiu.othello;

import com.github.lsiu.othello.game.OthelloBoard;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class GameController {

    private final OthelloBoard othelloBoard;

    public GameController(OthelloBoard othelloBoard) {
        this.othelloBoard = othelloBoard;
    }

    @ShellMethod(value = "Create a new game", key = "new")
    public String newGame() {
        othelloBoard.newGame();
        return othelloBoard.toString();
    }
}
