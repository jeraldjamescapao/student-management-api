package com.jeraldjamescapao.studentmanagementapi.dto.grade;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO used when creating a new {@code Grade} record through the API.
 *
 * <p><b>Note:</b> System-managed fields (e.g., {@code id}, {@code createdAt},
 * {@code updatedAt}, {@code deletedAt}) are handled internally and are not part of this payload.</p>
 *
 * <p><b>Validation:</b> Uses Jakarta Bean Validation annotations
 * (e.g., {@code @NotNull}, {@code @NotBlank}) to ensure that the input data
 * meets all required constraints before being processed.</p>
 *
 * <p><b>Swagger:</b> The {@link io.swagger.v3.oas.annotations.media.Schema @Schema}
 * annotations provide example values and descriptions for automatic OpenAPI documentation.</p>
 *
 * @see GradeUpdateRequest
 * @see GradeResponse
 */
@Schema(name = "GradeCreateRequest", description = "Payload to record a new grade for an enrollment.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeCreateRequest {

    @Schema(description = "Associated enrollment identifier.",
            example = "f1b2c3d4-1111-2222-3333-444455556666",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private UUID enrollmentId;

    @Schema(description = "Allowed examples: A, A-, B+, C, D, F, I (Incomplete), W (Withdrawn).",
            example = "A", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 2)
    private String letter;

    @Schema(description = "Points on a 0.00–6.00 scale, up to 2 decimals.",
            example = "5.50", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("6.00")
    @Digits(integer = 1, fraction = 2)
    private BigDecimal points;

    @Schema(description = "When the grade was assigned; optional.",
            example = "2025-11-01T10:00:00+01:00")
    private OffsetDateTime gradedAt;

    @Schema(description = "Optional free-text notes/remarks.",
            example = "Excellent final project.")
    @Size(max = 2000)
    private String notes;
}
