package com.jeraldjamescapao.studentmanagementapi.dto.enrollment;

import com.jeraldjamescapao.studentmanagementapi.entity.enums.EnrollmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

/**
 * DTO used when creating a new {@code Enrollment} record through the API.
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
 * @see EnrollmentUpdateRequest
 * @see EnrollmentResponse
 */
@Schema(name = "EnrollmentCreateRequest", description = "Payload to create a new enrollment.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentCreateRequest {

    @Schema(example = "cbe1e180-95a3-4c46-b2de-f21e07f3b351", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private UUID studentId;

    @Schema(example = "a6f0b11a-4b1e-4e15-8f5b-0d1a6f2e9e21", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private UUID courseId;

    @Schema(example = "2025-FALL", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 20)
    private String term;

    @Schema(example = "A", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 10)
    private String section;

    /** Optional initial status (defaults applied in service if null). */
    @Schema(description = "Enum value (case-insensitive as configured).", example = "REGISTERED")
    private EnrollmentStatus status;
}
