package com.jeraldjamescapao.studentmanagementapi.dto.student;

import com.jeraldjamescapao.studentmanagementapi.entity.enums.Gender;
import com.jeraldjamescapao.studentmanagementapi.entity.enums.StudentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;

/**
 * DTO used when updating or replacing an existing {@code Student} record
 * via the API (PUT-style request).
 *
 * <p>All fields are required to represent the full state of the resource.
 * If partial updates are desired (PATCH-style), a different DTO can be introduced later.</p>
 *
 * <p><b>Note:</b> System-managed fields (e.g., {@code id}, {@code createdAt},
 * {@code updatedAt}, {@code deletedAt}) are not part of this payload.</p>
 *
 * <p><b>Validation:</b> Uses Jakarta Bean Validation annotations
 * to ensure that updated data remains valid and consistent with
 * domain constraints.
 *
 * <p><b>Swagger:</b> The {@link io.swagger.v3.oas.annotations.media.Schema @Schema}
 * annotations provide example values and descriptions for automatic OpenAPI documentation.</p>
 *
 * @see StudentCreateRequest
 * @see StudentResponse
 */
@Schema(name = "StudentUpdateRequest", description = "Payload to replace/update a student.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentUpdateRequest {

    @Schema(example = "Jerald James", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 100)
    private String firstName;

    @Schema(example = "Capao", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 100)
    private String lastName;

    /** Unique email address for contact/identity. */
    @Schema(example = "jjcapaodev@protonmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Email
    @Size(max = 320)
    private String email;

    @Schema(description = "Enum value (case-insensitive as configured).",
            example = "MALE", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Gender gender;

    /** Date of birth (date-only, no timezone); ISO format (YYYY-MM-DD). */
    @Schema(example = "2000-12-25", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Past
    private LocalDate birthDate;

    @Schema(description = "Enum value (case-insensitive as configured).",
            example = "ENROLLED", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private StudentStatus status;
}
