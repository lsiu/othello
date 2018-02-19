package com.github.lsiu.othello.steps;

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

    private Object shellResult;

    public StepsDefinition() {
        When("^(.*) command entered$", (String command) ->
                shellResult = shell.evaluate(() -> command)
        );

        Then("^shell output contains:$", (String expectedOutput) ->
                Assert.assertThat(shellResult, CoreMatchers.equalTo(expectedOutput))
        );
    }
}
