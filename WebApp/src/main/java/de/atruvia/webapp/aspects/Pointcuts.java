package de.atruvia.webapp.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {


    @Pointcut(value = "execution (public * de.atruvia.webapp.presentation.controller.v1.PersonenController.*(..))")
    public void PersonenControllerMethods(){}

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void serviceMethods(){}

    @Pointcut("@within(de.atruvia.webapp.aspects.Benchmark)")
    public void benchmarkOperations(){}

}
