package com.github.lsiu.othello;

import com.github.lsiu.othello.game.LocationStatus;
import com.github.lsiu.othello.game.OthelloGame;
import lombok.Getter;
import lombok.Setter;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class GameController {

    @Getter
    @Setter
    private OthelloGame othelloGame;

    @ShellMethod(value = "Create a new game", key = "new")
    public String newGame() {
        othelloGame = new OthelloGame();
        return othelloGame.getBoard().toString();
    }

    @ShellMethod(value = "Make a move", key = "move")
    public String move(String move) {
        LocationStatus player = othelloGame.getTurn();
        othelloGame.move(move);
        return MoveDisplayUtils.generateMoveOutput(move, player, othelloGame.getBoard());
    }
}
