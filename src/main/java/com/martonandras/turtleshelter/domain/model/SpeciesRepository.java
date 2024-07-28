package com.martonandras.turtleshelter.domain.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository of {@link Species} entity
 */
public interface SpeciesRepository extends CrudRepository<Species, Long> {
}
