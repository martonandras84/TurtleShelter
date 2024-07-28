package com.martonandras.turtleshelter.application;

public interface SpeciesService {

    /**
     * Validate if Species records exists having specified id.
     * Throws TurtleProcessingException if record not found.
     *
     * @param speciesId species id
     * @return true if requested Species record exists
     */
    boolean throwExceptionIfSpeciesNotFound(Long speciesId);
}
