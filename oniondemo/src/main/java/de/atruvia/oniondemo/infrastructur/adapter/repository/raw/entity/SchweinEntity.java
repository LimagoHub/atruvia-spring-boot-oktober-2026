package de.atruvia.oniondemo.infrastructur.adapter.repository.raw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
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

    @Version
    private Long version;
    @Column(nullable = false)
    private int gewicht;

    private Instant deletedAt;
}
