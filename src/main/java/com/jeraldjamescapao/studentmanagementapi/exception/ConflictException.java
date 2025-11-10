package com.jeraldjamescapao.studentmanagementapi.exception;

/**
 * Thrown when a request breaks a unique rule or state rule.
 */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
