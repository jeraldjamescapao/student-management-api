package com.jeraldjamescapao.studentmanagementapi.exception;

import com.jeraldjamescapao.studentmanagementapi.web.GlobalExceptionHandler;

/**
 * Exception thrown when a requested resource does not exist in the system.
 *
 * <p><b>Usage:</b> Typically raised by service-layer methods when an entity
 * lookup by ID or unique field fails to return a result. It signals a
 * client-side error (HTTP 404).</p>
 *
 * <p><b>Handling:</b> Automatically converted to a
 * {@code 404 Not Found} response by
 * {@link GlobalExceptionHandler}.</p>
 *
 * @see GlobalExceptionHandler
 */
public class NotFoundException extends RuntimeException {

    /**
     * Creates a new exception with a message such as
     * {@code "Student not found: {id}"}.
     *
     * @param resource the entity or resource name
     * @param id the identifier that was not found
     */
    public NotFoundException(String resource, Object id) {
        super(resource + " not found: " + id);
    }
}
