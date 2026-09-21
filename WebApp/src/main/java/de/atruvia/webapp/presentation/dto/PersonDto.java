package de.atruvia.webapp.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

    @NotNull
    private UUID id;
    @NotNull
    @Size(min = 2, max = 50)
    private String vorname;
    @NotNull
    @Size(min = 2, max = 50)
    private String nachname;
}
