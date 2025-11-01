package com.jeraldjamescapao.studentmanagementapi.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeraldjamescapao.studentmanagementapi.entity.enums.Gender;
import com.jeraldjamescapao.studentmanagementapi.entity.enums.StudentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

import static com.jeraldjamescapao.studentmanagementapi.dto.common.ApiDateFormats.OFFSET_DATE_TIME;

/**
 * DTO returned by the API when reading or listing {@code Student} resources.
 *
 * <p>Represents the public-facing view of a {@code Student}, including identifiers
 * and audit timestamps ({@code createdAt}, {@code updatedAt}) populated by the system.</p>
 *
 * <p><b>Serialization:</b> Uses Jackson annotations such as
 * {@link com.fasterxml.jackson.annotation.JsonFormat @JsonFormat}
 * to format date and time fields for API output.</p>
 *
 * <p><b>Swagger:</b> The {@link io.swagger.v3.oas.annotations.media.Schema @Schema}
 * annotations provide example values and descriptions for automatic OpenAPI documentation.</p>
 *
 * @see StudentCreateRequest
 * @see StudentUpdateRequest
 */
@Schema(name = "StudentResponse", description = "Represents a student returned by the API.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {

    @Schema(description = "Unique identifier of the student.",
            example = "cbe1e180-95a3-4c46-b2de-f21e07f3b351")
    private UUID id;

    @Schema(example = "Jerald James")
    private String firstName;

    @Schema(example = "Capao")
    private String lastName;

    /** Unique email address for contact/identity. */
    @Schema(example = "jjcapaodev@protonmail.com")
    private String email;

    @Schema(description = "Enum value (case-insensitive as configured).",
            example = "MALE")
    private Gender gender;

    /** Date of birth (date-only, no timezone); ISO format (YYYY-MM-DD). */
    @Schema(example = "2000-12-25")
    private LocalDate birthDate;

    @Schema(description = "Enum value (case-insensitive as configured).",
            example = "ENROLLED")
    private StudentStatus status;

    /** Timestamp of record creation. */
    @Schema(example = "2025-11-01T09:45:00+01:00")
    @JsonFormat(pattern = OFFSET_DATE_TIME)
    private OffsetDateTime createdAt;

    /** Timestamp of the most recent update. */
    @Schema(example = "2025-11-02T14:15:30+01:00")
    @JsonFormat(pattern = OFFSET_DATE_TIME)
    private OffsetDateTime updatedAt;
}
