package de.db.webapp.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Profile("production")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
//@Qualifier("upper")
public class ToUpperTranslator implements Translator{
    @Override
    public String translate(String message) {
        return message.toUpperCase();
    }

    @PostConstruct
    public void init() {
        System.out.println("init ToUpperTranslator");
    }

    @PreDestroy
    public void dispose() {
        System.out.println("cleanup ToUpperTranslator");
    }
}
