package com.jeraldjamescapao.studentmanagementapi.exception;

/**
 * Thrown when input is invalid for the action.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
