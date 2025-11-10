package com.jeraldjamescapao.studentmanagementapi.exception;

/**
 * Thrown when a resource does not exist.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Build a "not found" message like "Student not found: {id}".
     */
    public NotFoundException(String resource, Object id) {
        super(resource + " not found: " + id);
    }
}
