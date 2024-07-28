package com.martonandras.turtleshelter.infrastructure.validation;

import com.martonandras.turtleshelter.infrastructure.validation.exception.InvalidWeightException;
import com.martonandras.turtleshelter.infrastructure.validation.exception.TurtleProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Exception handler using {@link ControllerAdvice}
 *
 * @author Andras Marton (martonandras84@gmail.com)
 */
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TurtleProcessingException.class)
    public ResponseEntity<Object> handleTurtleServiceException(TurtleProcessingException ex) {

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("message", ex.getMessage());

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidWeightException.class)
    public ResponseEntity<Object> handleInvalidUnitException(InvalidWeightException ex) {

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("message", ex.getMessage());

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
