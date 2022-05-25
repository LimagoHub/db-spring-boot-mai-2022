package de.db.webapp.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class XYZ {

    private final Demo demo;

    @PostConstruct
    public void init() {
        demo.run();
    }
}
