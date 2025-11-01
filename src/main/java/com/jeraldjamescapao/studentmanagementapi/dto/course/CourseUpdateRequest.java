package com.jeraldjamescapao.studentmanagementapi.dto.course;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * DTO used when updating or replacing an existing {@code Course} record
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
 * @see CourseCreateRequest
 * @see CourseResponse
 */
@Schema(name = "CourseUpdateRequest", description = "Payload to replace/update a course.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseUpdateRequest {

    @Schema(example = "CS101", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 20)
    private String code;

    @Schema(example = "Introduction to Computer Science", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 200)
    private String title;

    @Schema(example = "Basic principles of computing, algorithms, and programming.")
    @Size(max = 2000)
    private String description;

    @Schema(example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Min(0)
    @Max(30)
    private Integer credits;

    @Schema(example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Boolean active;
}
