package de.db.webapp.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Component
//@Named
//@Lazy
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class Demo {

    @Value("${Demo.message}")
    private String name = "Peter";

    private final Translator translator;


    //@Autowired
    public Demo(/* @Qualifier("upper")*/   final Translator translator) {
        this.translator = translator;
        System.out.println(translator.translate("CTOR von Demo" + name));
    }

    @PostConstruct
    public void init() {
        System.out.println(translator.translate("init von Demo" + name));
    }
}
