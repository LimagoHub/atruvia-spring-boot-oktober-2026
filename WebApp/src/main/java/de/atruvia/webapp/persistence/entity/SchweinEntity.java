package de.atruvia.webapp.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_schweine")
public class SchweinEntity {
    @Id
    private UUID id;
    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private int gewicht;
}
