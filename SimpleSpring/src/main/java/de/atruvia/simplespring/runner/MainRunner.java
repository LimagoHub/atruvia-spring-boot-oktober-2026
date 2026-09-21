package de.atruvia.simplespring.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class MainRunner implements CommandLineRunner {
    @Override
    public void run(final String... args) throws Exception {


        // Bitte mit dem Calculator 2 zahlen addieren und in die Console ausgeben


        System.out.println("Hello World");
    }
}
