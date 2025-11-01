package com.jeraldjamescapao.studentmanagementapi.dto.common;

/**
 * Centralized constants for defining date and time formats used
 * across all DTO classes for JSON serialization and documentation.
 *
 * <p>These formats ensure that all date/time values are consistent
 * in API responses and requests. They are referenced in
 * {@link com.fasterxml.jackson.annotation.JsonFormat @JsonFormat}
 * annotations throughout the DTO layer.</p>
 */
public final class ApiDateFormats {
    private ApiDateFormats() {}

    // Example output: 2025-11-01T12:00:00+01:00
    public static final String OFFSET_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssXXX";

    // Example: 1995-12-17
    public static final String DATE_ONLY = "yyyy-MM-dd";
}