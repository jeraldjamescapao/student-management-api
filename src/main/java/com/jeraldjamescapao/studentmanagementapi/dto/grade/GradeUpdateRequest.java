package com.jeraldjamescapao.studentmanagementapi.dto.grade;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * DTO used when updating or replacing an existing {@code Grade} record
 * via the API (PUT-style request).
 *
 * <p>All required fields must be present to represent the full state of the resource.
 * If partial updates are desired (PATCH-style), a different DTO can be introduced later.</p>
 *
 * <p><b>Note:</b> System-managed fields (e.g., {@code id}, {@code createdAt},
 * {@code updatedAt}, {@code deletedAt}, {@code enrollmentId}) are not part of this payload.</p>
 *
 * <p><b>Validation:</b> Uses Jakarta Bean Validation annotations to ensure that
 * updated data remains valid and consistent with domain constraints.</p>
 *
 * <p><b>Swagger:</b> The {@link io.swagger.v3.oas.annotations.media.Schema @Schema}
 * annotations provide example values and descriptions for automatic OpenAPI documentation.</p>
 *
 * @see GradeCreateRequest
 * @see GradeResponse
 */
@Schema(name = "GradeUpdateRequest", description = "Payload to replace/update a grade.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeUpdateRequest {

    @Schema(description = "Allowed examples: A, A-, B+, C, D, F, I (Incomplete), W (Withdrawn).",
            example = "A-", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 2)
    private String letter;

    @Schema(description = "Points on a 0.00–6.00 scale, up to 2 decimals.",
            example = "5.25", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("6.00")
    @Digits(integer = 1, fraction = 2)
    private BigDecimal points;

    @Schema(description = "When the grade was assigned; optional.",
            example = "2025-11-01T11:30:00+01:00")
    private OffsetDateTime gradedAt;

    @Schema(description = "Optional free-text notes.",
            example = "Adjusted after re-evaluation.")
    @Size(max = 2000)
    private String notes;
}
