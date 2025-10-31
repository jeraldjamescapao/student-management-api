package com.jeraldjamescapao.studentmanagementapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "courses",
        uniqueConstraints = @UniqueConstraint(name = "uq_courses_code", columnNames = "code"),
        indexes = @Index(name = "ix_courses_active", columnList = "active")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "Represents a course offered by the school.")
public class Course extends BaseEntity {

    @Schema(description = "Course code (e.g., CS101).")
    @Column(name = "code", length = 32, nullable = false)
    private String code;

    @Schema(description = "Course title.")
    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Schema(description = "Brief description of the course.")
    @Column(name = "description", length = 2000)
    private String description;

    @Schema(description = "Number of credit units for the course.")
    @Column(name = "credits", nullable = false)
    private Integer credits;

    @Schema(description = "Indicates if the course is currently active.")
    @Column(name = "active", nullable = false)
    private boolean active = true;
}
