package com.martonandras.turtleshelter.application;

import com.martonandras.turtleshelter.application.dto.CreateTurtleRequestDto;
import com.martonandras.turtleshelter.application.dto.TurtleResponseDto;
import com.martonandras.turtleshelter.application.dto.UpdateTurtleRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TurtleService {

    /**
     * Get details of all active turtles
     *
     * @return {@link Page} of {@link TurtleResponseDto}s that are requested
     */
    Page<TurtleResponseDto> listAllActiveTurtles(Pageable pageable);

    /**
     * Get details of all turtles of the specified species
     *
     * @return {@link Page} of {@link TurtleResponseDto}s that are requested
     */
    Page<TurtleResponseDto> listTurtlesBySpecies(Pageable pageable, Long speciesId);

    /**
     * Get details of turtle having specified id
     * @param turtleId id fo Turtle to be queried
     *
     * @return {@link TurtleResponseDto} that is requested
     */
    Optional<TurtleResponseDto> getTurtleInfo(Long turtleId);

    /**
     * Validate given requestDto and persist a new Turtle record in the database
     * @param requestDto request data
     * @return {@link TurtleResponseDto} of the Turtle persisted
     */
    Optional<TurtleResponseDto> createTurtle(CreateTurtleRequestDto requestDto);

    /**
     * Validate given requestDTO and updates Turtle object having given id
     * @param turtleId turtle id
     * @param requestDto request dto
     * @return {@link TurtleResponseDto} of the Turtle updated
     */
    Optional<TurtleResponseDto> updateTurtle(Long turtleId, UpdateTurtleRequestDto requestDto);

    /**
     * Update Turtle having given id to deleted
     * @param turtleId turtle id
     * @return {@link TurtleResponseDto} of the Turtle marked as deleted
     */
    Optional<TurtleResponseDto> deleteTurtle(Long turtleId);
}
