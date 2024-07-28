package com.martonandras.turtleshelter.domain.model;

import com.martonandras.turtleshelter.application.dto.CreateTurtleRequestDto;
import com.martonandras.turtleshelter.application.dto.UpdateTurtleRequestDto;
import com.martonandras.turtleshelter.domain.shared.AbstractEntity;
import com.martonandras.turtleshelter.infrastructure.MassConverter;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.ZonedDateTime;


/**
 * Entity of Turtle object
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
@Builder
@Getter
@Setter
@Table(name = "TURTLE", indexes = {@Index(name = "TURTLE_U1", columnList = "TURTLE_ID", unique = true),
        @Index(name = "TASK_N1", columnList = "NAME"),
        @Index(name = "TASK_N2", columnList = "IS_DELETED"),
        @Index(name = "TASK_N3", columnList = "SPECIES_ID")})
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Turtle extends AbstractEntity {

    @Id
    @Column(name = "TURTLE_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turtle-seq-gen")
    @SequenceGenerator(name = "turtle-seq-gen", sequenceName = "TURTLE_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "SPECIES_ID", nullable = false)
    private Long speciesId;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "ARRIVAL_DATE", nullable = false)
    private ZonedDateTime arrivalDate;

    @Column(name = "WEIGHT", nullable = false)
    private Long weight;

    @Column(name = "AGE", nullable = false)
    private Long age;

    @Column(name = "IS_DELETED", nullable = false)
    private boolean isDeleted;

    public static Turtle of(CreateTurtleRequestDto dto) {
        return Turtle.builder()
                .speciesId(dto.getSpeciesId())
                .name(dto.getName())
                .arrivalDate(dto.getArrivalDate())
                .weight(MassConverter.convertToGram(dto.getWeight(), dto.getMeasurementUnitOfWeight()))
                .age(dto.getAge())
                .isDeleted(false)
                .build();
    }

    public static Turtle of(UpdateTurtleRequestDto dto) {
        return Turtle.builder()
                .speciesId(dto.getSpeciesId())
                .name(dto.getName())
                .arrivalDate(dto.getArrivalDate())
                .weight(MassConverter.convertToGram(dto.getWeight(), dto.getMeasurementUnitOfWeight()))
                .age(dto.getAge())
                .isDeleted(false)
                .build();
    }

    public void deleteTurtle() {
        this.isDeleted = true;
    }

    public boolean merge(Turtle other) {
        if (other == null) {
            return false;
        }

        if (!new EqualsBuilder()
                .append(this.speciesId, other.speciesId)
                .append(this.name, other.name)
                .append(this.arrivalDate, other.arrivalDate)
                .append(this.weight, other.weight)
                .append(this.age, other.age)
                .isEquals()) {

            this.speciesId = ObjectUtils.isEmpty(other.speciesId) ? this.speciesId : other.speciesId;
            this.name = ObjectUtils.isEmpty(other.name) ? this.name : other.name;
            this.arrivalDate = ObjectUtils.isEmpty(other.arrivalDate) ? this.arrivalDate : other.arrivalDate;
            this.weight = ObjectUtils.isEmpty(other.weight) ? this.weight : other.weight;
            this.age = ObjectUtils.isEmpty(other.age) ? this.age : other.age;

            return true;
        }

        return false;
    }
}
