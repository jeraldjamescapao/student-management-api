package com.jeraldjamescapao.studentmanagementapi.entity;

import com.jeraldjamescapao.studentmanagementapi.entity.enums.EnrollmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "enrollments",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_enrollments",
                columnNames = {"student_id", "course_id", "term", "section"}
        ),
        indexes = {
                @Index(name = "ix_enrollments_student", columnList = "student_id"),
                @Index(name = "ix_enrollments_course", columnList = "course_id"),
                @Index(name = "ix_enrollments_status", columnList = "status")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "Represents a student's enrollment in a specific course.")
public class Enrollment extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_enrollments_student"))
    @Schema(description = "The student who is enrolled.")
    private Student student;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_enrollments_course"))
    @Schema(description = "The course the student is enrolled in.")
    private Course course;

    @Schema(description = "Academic term (e.g., 2025-FALL).")
    @Column(name = "term", length = 20, nullable = false)
    private String term;

    @Schema(description = "Course section (e.g., A, B1, LEC-01).")
    @Column(name = "section", length = 10, nullable = false)
    private String section;

    @Schema(description = "Current enrollment status.")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private EnrollmentStatus status;
}
