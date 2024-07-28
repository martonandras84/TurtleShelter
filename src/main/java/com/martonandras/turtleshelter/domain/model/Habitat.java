package com.martonandras.turtleshelter.domain.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity of habitat object
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
@Builder
@Getter
@Setter
@Table(name = "HABITAT", indexes = {@Index(name = "HABITAT_U1", columnList = "HABITAT_ID", unique = true)})
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Habitat {

    @Id
    @Column(name = "HABITAT_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habitat-seq-gen")
    @SequenceGenerator(name = "habitat-seq-gen", sequenceName = "HABITAT_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "TYPE", nullable = false, length = 200)
    private String type;
}
