package com.jeraldjamescapao.studentmanagementapi.dto.course;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * DTO used when creating a new {@code Course} record through the API.
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
 * @see CourseUpdateRequest
 * @see CourseResponse
 */
@Schema(name = "CourseCreateRequest", description = "Payload to create a new course.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCreateRequest {

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

    /** Optional; service layer may default this if null (e.g., true). */
    @Schema(example = "true")
    private Boolean active;
}
