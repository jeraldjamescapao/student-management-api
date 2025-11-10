package com.jeraldjamescapao.studentmanagementapi.exception;

import com.jeraldjamescapao.studentmanagementapi.web.GlobalExceptionHandler;

/**
 * Exception thrown when a request violates a unique constraint
 * or business rule that prevents the operation from completing.
 *
 * <p><b>Usage:</b> Commonly thrown when attempting to create or update
 * a record that would duplicate an existing unique field (e.g., email).</p>
 *
 * <p><b>Handling:</b> Automatically mapped to a
 * {@code 409 Conflict} response by
 * {@link GlobalExceptionHandler}.</p>
 *
 * @see GlobalExceptionHandler
 */
public class ConflictException extends RuntimeException {

    /**
     * Creates a new conflict exception with a short descriptive message.
     *
     * @param message the reason for the conflict
     */
    public ConflictException(String message) {
        super(message);
    }
}
