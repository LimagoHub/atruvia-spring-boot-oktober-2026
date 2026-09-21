package de.atruvia.simplespring.runner;

import de.atruvia.simplespring.math.Calculator;
import de.atruvia.simplespring.pojo.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.UUID;

//@Component
//@RequiredArgsConstructor

public class MainRunner implements CommandLineRunner {




    @Qualifier("secure")
    private final Calculator calculator;

    public MainRunner(final Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void run(final String... args) throws Exception {



        // Bitte mit dem Calculator 2 zahlen addieren und in die Console ausgeben


        System.out.println(calculator.add(1, 2));
    }
}
