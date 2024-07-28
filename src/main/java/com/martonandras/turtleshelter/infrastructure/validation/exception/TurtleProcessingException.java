package com.martonandras.turtleshelter.infrastructure.validation.exception;

/**
 * Custom runtime exception thrown by validation errors.
 * Static error messages included.
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
public class TurtleProcessingException extends RuntimeException {

    public final static String TURTLE_NOT_FOUND = "Turtle not found with id %s";
    public final static String TURTLE_ALREADY_DELETED = "Turtle is already deleted with id %s";
    public final static String SPECIES_NOT_FOUND = "Species not found with id %s";

    public TurtleProcessingException(String errorMessage) {
        super(errorMessage);
    }
}
