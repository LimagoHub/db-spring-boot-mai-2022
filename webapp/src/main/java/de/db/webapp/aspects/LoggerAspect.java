package de.db.webapp.aspects;

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
public class LoggerAspect {



    @Before("Pointcuts.restControllerMethodes()")
    public void logAdvice(JoinPoint joinPoint) {
        log.warn("Hallo Aspect " + joinPoint.getSignature().getName() + " wurde aufgerufen");
    }
    @AfterReturning(value = "execution(public * de.db.webapp.presentation.PersonenController.*(..))",returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.warn(result.toString());
    }
    @AfterThrowing(value = "execution(public * de.db.webapp.presentation.PersonenController.*(..))",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("Fehler {} ist aufgetreten", ex);
    }

    @Around("execution(public * de.db.webapp.presentation.PersonenController.*(..))")
    public Object benchmark(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        var start = Instant.now();
        Object result = proceedingJoinPoint.proceed();
        var end = Instant.now();
        var duration = Duration.between(start,end);
        System.out.println(duration.toMillis());
        return result;
    }
}
