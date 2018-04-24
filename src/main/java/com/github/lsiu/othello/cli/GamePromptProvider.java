package com.github.lsiu.othello.cli;

import com.github.lsiu.othello.game.GameRepository;
import com.github.lsiu.othello.game.OthelloGame;
import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class GamePromptProvider implements PromptProvider {

    private final GameRepository gameRepository;

    public GamePromptProvider(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public AttributedString getPrompt() {
        OthelloGame game = gameRepository.getTheOnlyGame();
        if (game != null) {
            return new AttributedString(String.format("Player '%s' move: ", game.getTurn().toString()));
        } else {
            return new AttributedString("No game in progress. Type 'new' to start a game: ");
        }
    }
}
