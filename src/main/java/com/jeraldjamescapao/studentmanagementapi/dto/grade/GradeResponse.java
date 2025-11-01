package com.jeraldjamescapao.studentmanagementapi.dto.grade;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static com.jeraldjamescapao.studentmanagementapi.dto.common.ApiDateFormats.OFFSET_DATE_TIME;

/**
 * DTO returned by the API when reading or listing {@code Grade} resources.
 *
 * <p>Represents the public-facing view of a {@code Grade}, including identifier,
 * associated {@code enrollmentId}, the grade letter and points, and audit timestamps
 * ({@code createdAt}, {@code updatedAt}) populated by the system.</p>
 *
 * <p><b>Serialization:</b> Uses Jackson annotations such as
 * {@link com.fasterxml.jackson.annotation.JsonFormat @JsonFormat}
 * to format date and time fields for API output.</p>
 *
 * <p><b>Swagger:</b> The {@link io.swagger.v3.oas.annotations.media.Schema @Schema}
 * annotations provide example values and descriptions for automatic OpenAPI documentation.</p>
 *
 * @see GradeCreateRequest
 * @see GradeUpdateRequest
 */
@Schema(name = "GradeResponse", description = "Represents a grade returned by the API.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeResponse {

    @Schema(description = "Unique identifier of the grade.",
            example = "9f2b1a44-8888-7777-6666-555544443333")
    private UUID id;

    @Schema(description = "Associated enrollment identifier.",
            example = "f1b2c3d4-1111-2222-3333-444455556666")
    private UUID enrollmentId;

    @Schema(description = "Allowed examples: A, A-, B+, C, D, F, I (Incomplete), W (Withdrawn).",
            example = "A")
    private String letter;

    @Schema(description = "Points on a 0.00–6.00 scale, up to 2 decimals.",
            example = "5.50")
    private BigDecimal points;

    @Schema(description = "When the grade was assigned; may be null.",
            example = "2025-11-01T10:00:00+01:00")
    @JsonFormat(pattern = OFFSET_DATE_TIME)
    private OffsetDateTime gradedAt;

    /** Timestamp of record creation. */
    @Schema(example = "2025-11-01T09:45:00+01:00")
    @JsonFormat(pattern = OFFSET_DATE_TIME)
    private OffsetDateTime createdAt;

    /** Timestamp of the most recent update. */
    @Schema(example = "2025-11-02T14:15:30+01:00")
    @JsonFormat(pattern = OFFSET_DATE_TIME)
    private OffsetDateTime updatedAt;

    @Schema(description = "Optional free-text notes/remarks.",
            example = "Excellent final project.")
    private String notes;
}
