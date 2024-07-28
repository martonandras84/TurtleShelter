package com.martonandras.turtleshelter.domain.model;

import com.martonandras.turtleshelter.domain.shared.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity of Species object
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
@Builder
@Getter
@Setter
@Table(name = "SPECIES", indexes = {@Index(name = "SPECIES_U1", columnList = "SPECIES_ID", unique = true)})
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Species extends AbstractEntity {

    @Id
    @Column(name = "SPECIES_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "species-seq-gen")
    @SequenceGenerator(name = "species-seq-gen", sequenceName = "SPECIES_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "HABITAT_ID", nullable = false)
    private Long habitatId;
}
