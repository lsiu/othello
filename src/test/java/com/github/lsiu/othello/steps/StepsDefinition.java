package com.github.lsiu.othello.steps;

import com.github.lsiu.othello.game.*;
import cucumber.api.java8.En;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;

@SpringBootTest
public class StepsDefinition implements En {

    @Autowired
    private Shell shell;

    @Autowired
    private GameRepository gameRepository;

    private Object shellResult;


    public StepsDefinition() {
        Given("^this is the state of the game and is (.*)'s turn$", (LocationStatus turn, String board) -> {
            OthelloBoard othelloBoard = TestUtils.setupBoard(board);
            gameRepository.setCurrentGameTo(new OthelloGame(othelloBoard, turn));
        });

        When("^(.*) command entered$", (String command) ->
                shellResult = shell.evaluate(() -> command)
        );

        Then("^shell output contains:$", (String expectedOutput) ->
                Assert.assertThat(shellResult, CoreMatchers.equalTo(expectedOutput))
        );

        Then("^expect game exception with message:$", (String message) -> {
            Assert.assertThat(shellResult, CoreMatchers.instanceOf(GameException.class));
            Assert.assertThat(((GameException) shellResult).getMessage(), CoreMatchers.equalTo(message));
        });

        Then("^it is (.*)'s turn$", (LocationStatus player) ->
                Assert.assertThat(gameRepository.getTheOnlyGame().getTurn(), CoreMatchers.equalTo(player))
        );
    }
}
