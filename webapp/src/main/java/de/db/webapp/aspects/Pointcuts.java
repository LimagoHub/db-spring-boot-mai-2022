package de.db.webapp.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(public * de.db.webapp.presentation.controllers.PersonenCommandController.*(..))")
    public void personenControllerMethodes(){}

    //@Pointcut(value = "within(@org.springframework.web.bind.annotation.RestController *)" )
    @Pointcut(value = "@within(org.springframework.web.bind.annotation.RestController)" )
    public void restControllerMethodes() {}
}
