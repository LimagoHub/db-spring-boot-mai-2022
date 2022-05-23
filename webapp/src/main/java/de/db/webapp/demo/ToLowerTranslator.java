package de.db.webapp.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
// @Qualifier("lower")
public class ToLowerTranslator implements Translator{


    @Override
    public String translate(String message) {
        return message.toLowerCase();
    }
}
