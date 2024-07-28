package com.martonandras.turtleshelter.infrastructure.validation.exception;

/**
 * Custom runtime exception thrown by validation errors.
 * Static error messages included.
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
public class InvalidWeightException extends RuntimeException {

    public final static String INVALID_UNIT = "Invalid unit of measurement %s";
    public static final String UNIT_OR_VALUE_MISSING = "Weight value or unit missing";

    public InvalidWeightException(String errorMessage) {
        super(errorMessage);
    }
}
