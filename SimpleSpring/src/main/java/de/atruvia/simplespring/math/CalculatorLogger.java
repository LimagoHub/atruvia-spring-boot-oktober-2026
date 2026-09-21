package de.atruvia.simplespring.math;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("logger")
@RequiredArgsConstructor
public class CalculatorLogger implements Calculator{

    @Qualifier("impl")
    private final Calculator calculator;



    @Override
    public double add(final double a, final double b) {
        System.out.println("Adding " + a + " and " + b);
        return calculator.add(a, b);
    }

    @Override
    public double sub(final double a, final double b) {
        return calculator.sub(a, b);
    }
}
