package com.github.lsiu.othello;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.shell.Shell;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StartNewGameOnAppStart implements ApplicationListener<ContextRefreshedEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationReadyEvent) {
        AtomicInteger count = new AtomicInteger(0);
        applicationReadyEvent.getApplicationContext().getBean(Shell.class).run(
                () -> count.incrementAndGet() == 1 ? () -> "new" : null
        );
    }
}
