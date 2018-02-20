package com.github.lsiu.othello;

import com.github.lsiu.othello.game.GameException;
import org.springframework.shell.result.TerminalAwareResultHandler;
import org.springframework.stereotype.Component;

@Component
public class GameExceptionResultHandler extends TerminalAwareResultHandler<GameException> {

    @Override
    protected void doHandleResult(GameException result) {
        this.terminal.writer().println(result.getMessage());
    }
}
