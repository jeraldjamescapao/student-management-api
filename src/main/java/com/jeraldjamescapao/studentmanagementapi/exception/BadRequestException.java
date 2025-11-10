package com.jeraldjamescapao.studentmanagementapi.exception;

import com.jeraldjamescapao.studentmanagementapi.web.GlobalExceptionHandler;

/**
 * Exception thrown when the client submits invalid input or an
 * unsupported operation.
 *
 * <p><b>Usage:</b> Used when the request format, parameters,
 * or logical conditions fail validation.</p>
 *
 * <p><b>Handling:</b> Automatically translated into a
 * {@code 400 Bad Request} response by
 * {@link GlobalExceptionHandler}.</p>
 *
 * @see GlobalExceptionHandler
 */
public class BadRequestException extends RuntimeException {

    /**
     * Creates a new bad request exception with a descriptive message.
     *
     * @param message the validation or rule error message
     */
    public BadRequestException(String message) {
        super(message);
    }
}
