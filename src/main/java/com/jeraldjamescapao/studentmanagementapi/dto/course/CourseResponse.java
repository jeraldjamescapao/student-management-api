package com.jeraldjamescapao.studentmanagementapi.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.jeraldjamescapao.studentmanagementapi.dto.common.ApiDateFormats.OFFSET_DATE_TIME;

/**
 * DTO returned by the API when reading or listing {@code Course} resources.
 *
 * <p>Represents the public-facing view of a {@code Course}, including identifier
 * and audit timestamps ({@code createdAt}, {@code updatedAt}) populated by the system.</p>
 *
 * <p><b>Serialization:</b> Uses Jackson annotations such as
 * {@link com.fasterxml.jackson.annotation.JsonFormat @JsonFormat}
 * to format date and time fields for API output.</p>
 *
 * <p><b>Swagger:</b> The {@link io.swagger.v3.oas.annotations.media.Schema @Schema}
 * annotations provide example values and descriptions for automatic OpenAPI documentation.</p>
 *
 * @see CourseCreateRequest
 * @see CourseUpdateRequest
 */
@Schema(name = "CourseResponse", description = "Represents a course returned by the API.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {

    @Schema(description = "Unique identifier of the course.",
            example = "a6f0b11a-4b1e-4e15-8f5b-0d1a6f2e9e21")
    private UUID id;

    @Schema(example = "CS101")
    private String code;

    @Schema(example = "Introduction to Computer Science")
    private String title;

    @Schema(example = "Basic principles of computing, algorithms, and programming.")
    private String description;

    @Schema(example = "3")
    private Integer credits;

    @Schema(example = "true")
    private boolean active;

    /** Timestamp of record creation. */
    @Schema(example = "2025-11-01T09:45:00+01:00")
    @JsonFormat(pattern = OFFSET_DATE_TIME)
    private OffsetDateTime createdAt;

    /** Timestamp of the most recent update. */
    @Schema(example = "2025-11-02T14:15:30+01:00")
    @JsonFormat(pattern = OFFSET_DATE_TIME)
    private OffsetDateTime updatedAt;
}
