package de.atruvia.webapp.presentation.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class SchweinDto {


    @NotNull
    private UUID id;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;


    @DecimalMin("10")
    private int gewicht;
}