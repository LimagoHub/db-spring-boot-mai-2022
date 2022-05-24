package de.db.webapp.presentation.dtos;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchweinDto {

    @NotNull
    @Size(min=36,max=36)
    private String id;

    @NotNull
    @Size(min=2,max=30)
    private String name;

    @NotNull
    @DecimalMin("10")
    private int gewicht;
}
