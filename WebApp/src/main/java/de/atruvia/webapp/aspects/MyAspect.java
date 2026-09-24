package de.atruvia.webapp.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Aspect
@Slf4j
public class MyAspect {



    @Before(value = "Pointcuts.PersonenControllerMethods()")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.warn("######### before Advice " + joinPoint.getSignature().getName() + " ##########" );
    }

    @Before(value = "Pointcuts.serviceMethods()")
    public void beforeServiceAdvice(JoinPoint joinPoint) {
        log.warn("_".repeat(10) + joinPoint.getSignature().getName() + "_".repeat(10) );
    }

    @AfterReturning(value = "Pointcuts.PersonenControllerMethods()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.warn("######### after returning Advice " + joinPoint.getSignature().getName() + " ##########" );
        log.warn(result.toString());
    }

    @AfterThrowing(value = "execution (public * de.atruvia.webapp.presentation.controller.v1.PersonenController.*(..))", throwing = "ex")
    public void afterReturning(JoinPoint joinPoint, Throwable ex) {
        log.warn("######### after Throwing Advice " + joinPoint.getSignature().getName() + " ##########" );
        log.warn(ex.toString());
    }

    @After(value = "execution (public * de.atruvia.webapp.presentation.controller.v1.PersonenController.*(..))")
    public void after(JoinPoint joinPoint) {
        log.warn("######### after Throwing Advice " + joinPoint.getSignature().getName() + " ##########" );

    }

    @Around(value = "de.atruvia.webapp.aspects.Pointcuts.benchmarkOperations()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        Instant start = Instant.now();
        var result =  joinPoint.proceed();
        Instant end = Instant.now();
        System.out.println("Duration of " + joinPoint.getSignature().getName() + " was " + Duration.between(start, end).toMillis() + " millis");
        return result;

    }
}
