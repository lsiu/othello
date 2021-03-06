package com.github.lsiu.othello.cli;

import com.github.lsiu.othello.game.GameRepository;
import com.github.lsiu.othello.game.OthelloGame;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @ShellMethod(value = "Create a new game", key = "new")
    public String newGame() {
        OthelloGame othelloGame = gameRepository.newGame();
        return othelloGame.getBoard().toString() + "\n";
    }

    @ShellMethod(value = "Make a move", key = "move")
    public String move(String move) {
        OthelloGame othelloGame = gameRepository.getTheOnlyGame();
        othelloGame.move(move);
        return othelloGame.getBoard().toString() + "\n";
    }
}
