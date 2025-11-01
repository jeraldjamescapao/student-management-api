package com.jeraldjamescapao.studentmanagementapi.dto.enrollment;

import com.jeraldjamescapao.studentmanagementapi.entity.enums.EnrollmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * DTO used when updating or replacing an existing {@code Enrollment} record
 * via the API (PUT-style request).
 *
 * <p>All fields are required to represent the full state of the resource.
 * If partial updates are desired (PATCH-style), a different DTO can be introduced later.</p>
 *
 * <p><b>Note:</b> System-managed fields (e.g., {@code id}, {@code createdAt},
 * {@code updatedAt}, {@code deletedAt}) are not part of this payload.</p>
 *
 * <p><b>Validation:</b> Uses Jakarta Bean Validation annotations to ensure that
 * updated data remains valid and consistent with domain constraints.</p>
 *
 * <p><b>Swagger:</b> The {@link io.swagger.v3.oas.annotations.media.Schema @Schema}
 * annotations provide example values and descriptions for automatic OpenAPI documentation.</p>
 *
 * @see EnrollmentCreateRequest
 * @see EnrollmentResponse
 */
@Schema(name = "EnrollmentUpdateRequest", description = "Payload to replace/update an enrollment.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentUpdateRequest {

    @Schema(example = "2025-FALL", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 20)
    private String term;

    @Schema(example = "A", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 10)
    private String section;

    @Schema(description = "Enum value (case-insensitive as configured).",
            example = "ENROLLED", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private EnrollmentStatus status;
}
