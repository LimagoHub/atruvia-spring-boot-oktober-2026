package de.atruvia.webapp.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class MyAspect {


    @Before(value = "execution (public * de.atruvia.webapp.presentation.controller.v1.PersonenController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.warn("######### before Advice " + joinPoint.getSignature().getName() + " ##########" );
    }

    @AfterReturning(value = "execution (public * de.atruvia.webapp.presentation.controller.v1.PersonenController.*(..))", returning = "result")
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

    @Around(value = "execution (public * de.atruvia.webapp.presentation.controller.v1.PersonenController.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        // Security check
        log.warn("######### around " + joinPoint.getSignature().getName() + " ##########" );
        return joinPoint.proceed();

    }
}
