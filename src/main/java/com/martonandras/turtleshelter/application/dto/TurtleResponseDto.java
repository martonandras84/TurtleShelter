package com.martonandras.turtleshelter.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martonandras.turtleshelter.domain.model.Turtle;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

/**
 * Response DTO for Turtle objects
 * All fields are mandatory
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
@Builder
@Getter
public class TurtleResponseDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "speciesId")
    private Long speciesId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "arrivalDate")
    private ZonedDateTime arrivalDate;

    @JsonProperty(value = "weight")
    private Long weight;

    @JsonProperty(value = "age")
    private Long age;

    @JsonProperty(value = "deleted")
    private boolean deleted;

    public static TurtleResponseDto of(Turtle turtle) {
        return TurtleResponseDto.builder()
                .id(turtle.getId())
                .speciesId(turtle.getSpeciesId())
                .name(turtle.getName())
                .arrivalDate(turtle.getArrivalDate())
                .weight(turtle.getWeight())
                .age(turtle.getAge())
                .deleted(turtle.isDeleted())
                .build();
    }
}
