package com.jeraldjamescapao.studentmanagementapi.dto.student;

import com.jeraldjamescapao.studentmanagementapi.entity.enums.Gender;
import com.jeraldjamescapao.studentmanagementapi.entity.enums.StudentStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

/**
 * DTO used when creating a new {@code Student} record through the API.
 *
 * <p><b>Note:</b> System-managed fields (e.g., {@code id}, {@code createdAt},
 * {@code updatedAt}, {@code deletedAt}) are handled internally and are not part of this payload.</p>
 *
 * <p><b>Validation:</b> Uses Jakarta Bean Validation annotations
 * (e.g., {@code @NotBlank}, {@code @NotNull}) to ensure that the input data
 * meets all required constraints before being processed.</p>
 *
 * <p><b>Swagger:</b> The {@link io.swagger.v3.oas.annotations.media.Schema @Schema}
 * annotations provide example values and descriptions for automatic OpenAPI documentation.</p>
 *
 * @see StudentUpdateRequest
 * @see StudentResponse
 */
@Schema(name = "StudentCreateRequest", description = "Payload to create a new student.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreateRequest {

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

    /** Optional initial status (defaults applied in service if null). */
    @Schema(description = "Enum value (case-insensitive as configured).",
            example = "ADMITTED")
    private StudentStatus status;
}
