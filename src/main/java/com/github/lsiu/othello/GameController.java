package com.github.lsiu.othello;

import com.github.lsiu.othello.game.Location;
import com.github.lsiu.othello.game.OthelloGame;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class GameController {

    private OthelloGame othelloGame;

    @ShellMethod(value = "Create a new game", key = "new")
    public String newGame() {
        othelloGame = OthelloGame.newGame();
        return othelloGame.getBoard().toString();
    }

    @ShellMethod(value = "Make a move", key = "move")
    public String move(Location location) {
        othelloGame.move(location);
        return othelloGame.getBoard().toString();
    }
}
