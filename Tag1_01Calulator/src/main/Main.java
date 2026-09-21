package main;

import client.CalcClient;
import math.Calculator;
import math.CalculatorImpl;
import math.CalculatorLogger;
import math.CalculatorSecure;
import shared.LoggerProxy;

public class Main {

    public static void main(String[] args) {

        Calculator calculator = new CalculatorImpl();
        //calculator = new CalculatorLogger(calculator);
        calculator = (Calculator) LoggerProxy.newInstance(calculator);
        calculator = new CalculatorSecure(calculator);
        CalcClient calcClient = new CalcClient(calculator);
        calcClient.go();
    }
}
