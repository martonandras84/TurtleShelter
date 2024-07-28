package com.martonandras.turtleshelter.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.martonandras.turtleshelter.application.TurtleService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.ZonedDateTime;

/**
 * Request DTO for Turtle creation
 * All fields are mandatory
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTurtleRequestDto {

    @JsonProperty(value = "speciesId")
    @NonNull
    private Long speciesId;

    @JsonProperty(value = "name")
    @NonNull
    private String name;

    @JsonProperty(value = "arrivalDate")
    @NonNull
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime arrivalDate;

    @JsonProperty(value = "weight")
    @NonNull
    private Long weight;

    @JsonProperty(value = "measurementUnitOfWeight")
    @NonNull
    private String measurementUnitOfWeight;

    @JsonProperty(value = "age")
    @NonNull
    private Long age;
}