package com.andreidemus.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Exceptions handler. Is used for mapping thrown exceptions to responses.
 */
@ControllerAdvice
public class NotesExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e, WebRequest request) {
        Error error = new Error(e.getMessage());
        return handleExceptionInternal(e, error, new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleOthers(Exception e, WebRequest request) {
        logger.error("Unexpected exception happened", e);
        Error error = new Error(e.getMessage());
        return handleExceptionInternal(e, error, new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
    }
}
