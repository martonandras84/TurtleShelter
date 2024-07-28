package com.martonandras.turtleshelter.domain.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository of {@link Turtle} entity
 */
public interface TurtleRepository extends CrudRepository<Turtle, Long> {

    /**
     * Query all {@link Turtle} records having isDeleted EQ FALSE
     *
     * @return {@link Page} of {@link Turtle}s
     */
    Page<Turtle> findAllByIsDeletedFalse(Pageable pageable);

    Page<Turtle> findAllBySpeciesId(Pageable pageable, Long speciesId);

    Optional<Turtle> findByName(String name);
}
