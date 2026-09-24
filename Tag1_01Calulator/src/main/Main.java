package main;

import client.CalcClient;
import math.Calculator;
import math.CalculatorImpl;
import math.CalculatorLogger;
import math.CalculatorSecure;
import shared.LoggerProxy;

import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args) {


        var start = Instant.now();


        Calculator calculator = new CalculatorImpl();
        calculator = new CalculatorLogger(calculator);
        //calculator = (Calculator) LoggerProxy.newInstance(calculator);
        calculator = new CalculatorSecure(calculator);
        CalcClient calcClient = new CalcClient(calculator);
        calcClient.go();

        var end = Instant.now();

        var duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }
}
