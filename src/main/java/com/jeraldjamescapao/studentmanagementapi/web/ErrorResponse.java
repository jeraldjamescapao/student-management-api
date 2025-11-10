package com.jeraldjamescapao.studentmanagementapi.web;

import java.time.OffsetDateTime;

/**
 * API error payload.
 */
public record ErrorResponse(
    int status,
    String error,
    String message,
    String path,
    OffsetDateTime timestamp
) {
    /**
     * Standard error body.
     */
    public static ErrorResponse of(int status, String error, String message, String path) {
        return new ErrorResponse(status, error, message, path, OffsetDateTime.now());
    }
}
