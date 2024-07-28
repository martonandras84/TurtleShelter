package com.martonandras.turtleshelter.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * Request DTO for Turtle update
 * Fields are nullable
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UpdateTurtleRequestDto {

    @JsonProperty(value = "speciesId")
    private Long speciesId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "arrivalDate")
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime arrivalDate;

    @JsonProperty(value = "weight")
    private Long weight;

    @JsonProperty(value = "measurementUnitOfWeight")
    private String measurementUnitOfWeight;

    @JsonProperty(value = "age")
    private Long age;
}
