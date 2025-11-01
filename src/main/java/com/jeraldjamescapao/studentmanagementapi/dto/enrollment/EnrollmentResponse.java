package com.jeraldjamescapao.studentmanagementapi.dto.enrollment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeraldjamescapao.studentmanagementapi.entity.enums.EnrollmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.jeraldjamescapao.studentmanagementapi.dto.common.ApiDateFormats.OFFSET_DATE_TIME;

/**
 * DTO returned by the API when reading or listing {@code Enrollment} resources.
 *
 * <p>Represents the public-facing view of an {@code Enrollment}, including identifiers,
 * foreign key references ({@code studentId}, {@code courseId}), and audit timestamps
 * ({@code createdAt}, {@code updatedAt}) populated by the system.</p>
 *
 * <p><b>Serialization:</b> Uses Jackson annotations such as
 * {@link com.fasterxml.jackson.annotation.JsonFormat @JsonFormat}
 * to format date and time fields for API output.</p>
 *
 * <p><b>Swagger:</b> The {@link io.swagger.v3.oas.annotations.media.Schema @Schema}
 * annotations provide example values and descriptions for automatic OpenAPI documentation.</p>
 *
 * @see EnrollmentCreateRequest
 * @see EnrollmentUpdateRequest
 */
@Schema(name = "EnrollmentResponse", description = "Represents an enrollment returned by the API.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponse {

    @Schema(description = "Unique identifier of the enrollment.",
            example = "f1b2c3d4-1111-2222-3333-444455556666")
    private UUID id;

    @Schema(description = "Associated student identifier.",
            example = "cbe1e180-95a3-4c46-b2de-f21e07f3b351")
    private UUID studentId;

    @Schema(description = "Associated course identifier.",
            example = "a6f0b11a-4b1e-4e15-8f5b-0d1a6f2e9e21")
    private UUID courseId;

    @Schema(example = "2025-FALL")
    private String term;

    @Schema(example = "A")
    private String section;

    @Schema(description = "Enum value (case-insensitive as configured).", example = "ENROLLED")
    private EnrollmentStatus status;

    /** Timestamp of record creation. */
    @Schema(example = "2025-11-01T09:45:00+01:00")
    @JsonFormat(pattern = OFFSET_DATE_TIME)
    private OffsetDateTime createdAt;

    /** Timestamp of the most recent update. */
    @Schema(example = "2025-11-02T14:15:30+01:00")
    @JsonFormat(pattern = OFFSET_DATE_TIME)
    private OffsetDateTime updatedAt;
}
