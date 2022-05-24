package de.db.webapp.services.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schwein {

    @Setter(AccessLevel.PRIVATE)
    private String id;

    @Setter(AccessLevel.PRIVATE)
    private String name;

    @Setter(AccessLevel.PRIVATE)
    private int gewicht;

    public void taufen(String name) {
        if("Elsa".equals(name))
            throw new IllegalArgumentException("Elsa heißt die Kuh!");
        setName(name);
    }

    public void fuettern() {
        setGewicht(getGewicht() + 1);
    }
}
