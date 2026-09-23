package de.atruvia.webapp.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuetterungDto {

    private UUID requestId;
    private int anzahlKartoffeln;
}
