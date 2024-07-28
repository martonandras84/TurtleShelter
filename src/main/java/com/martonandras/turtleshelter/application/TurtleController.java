package com.martonandras.turtleshelter.application;

import com.martonandras.turtleshelter.application.dto.CreateTurtleRequestDto;
import com.martonandras.turtleshelter.application.dto.TurtleResponseDto;
import com.martonandras.turtleshelter.application.dto.UpdateTurtleRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TurtleController {

    /**
     * Rest controller to get details of a specific turtle
     *
     * @param turtleId turtle id to be queried
     * @return {@link ResponseEntity} of {@link TurtleResponseDto} that is requested
     */
    ResponseEntity<TurtleResponseDto> getTurtle(Long turtleId);

    /**
     * Rest controller to list all turtles of the specified species
     *
     * @param speciesId species id to list the turtles to
     * @return {@link ResponseEntity} of {@link TurtleResponseDto} that is requested
     */
    ResponseEntity<Page<TurtleResponseDto>> listTurtles(Pageable pageable, Long speciesId);

    /**
     * Rest controller to create new turtle
     *
     * @param requestDto {@link CreateTurtleRequestDto} structure of input json
     * @return {@link ResponseEntity} of {@link TurtleResponseDto} that is created
     */
    ResponseEntity<TurtleResponseDto> createTurtle(CreateTurtleRequestDto requestDto);

    /**
     * Rest controller to update turtle
     *
     * @param turtleId turtle id to be updated
     * @param requestDto {@link UpdateTurtleRequestDto} structure of input json
     * @return {@link ResponseEntity} of {@link TurtleResponseDto} that is updated
     */
    ResponseEntity<TurtleResponseDto> updateTurtle(Long turtleId, UpdateTurtleRequestDto requestDto);

    /**
     * Rest controller to delete turtle
     *
     * @param turtleId turtle id
     * @return {@link ResponseEntity} of {@link TurtleResponseDto} that is marked as deleted
     */
    ResponseEntity<TurtleResponseDto> deleteTurtle(Long turtleId);
}
