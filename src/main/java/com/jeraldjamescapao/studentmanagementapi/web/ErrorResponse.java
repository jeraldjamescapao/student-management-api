package com.jeraldjamescapao.studentmanagementapi.web;

import java.time.OffsetDateTime;

/**
 * Standardized structure for all API error responses.
 *
 * <p><b>Usage:</b> Returned automatically when exceptions are
 * handled by {@link GlobalExceptionHandler GlobalExceptionHandler}.
 * Each field gives clear and consistent feedback to API consumers.</p>
 *
 * <p><b>Structure:</b></p>
 * <ul>
 *   <li>{@code status} – HTTP status code (e.g., 400, 404, 409)</li>
 *   <li>{@code error} – short reason phrase (e.g., "Bad Request")</li>
 *   <li>{@code message} – human-readable detail</li>
 *   <li>{@code path} – API path where the error occurred</li>
 *   <li>{@code timestamp} – time of occurrence (UTC)</li>
 * </ul>
 *
 * <p><b>Note:</b> Designed as a Java {@code record} for immutability
 * and concise serialization.</p>
 *
 * @see GlobalExceptionHandler
 */
public record ErrorResponse(
    int status,
    String error,
    String message,
    String path,
    OffsetDateTime timestamp
) {
    /**
     * Builds a new {@code ErrorResponse} with the given details.
     *
     * @param status the HTTP status code
     * @param error short reason phrase
     * @param message descriptive message
     * @param path the request URI
     * @return a new {@code ErrorResponse} instance with the current timestamp
     */
    public static ErrorResponse of(int status, String error, String message, String path) {
        return new ErrorResponse(status, error, message, path, OffsetDateTime.now());
    }
}
