package de.atruvia.simplespring.math;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("secure")
@RequiredArgsConstructor
public class CalculatorSecure implements Calculator{

    @Qualifier("logger")
    private final Calculator calculator;



    public double add(final double a, final double b) {
        System.out.println("Du kommst hier rein");
        return calculator.add(a, b);
    }

    public double sub(final double a, final double b) {
        return calculator.sub(a, b);
    }
}
