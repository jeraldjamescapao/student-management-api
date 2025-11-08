package com.jeraldjamescapao.studentmanagementapi.entity;

import com.jeraldjamescapao.studentmanagementapi.entity.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * JPA entity linking a {@code Student} to a {@code Course} for a specific academic offering (term and section).
 *
 * <p>Not directly exposed via the public API. Access is managed through controllers, DTOs, mappers,
 * and services that enforce business rules.</p>
 *
 * <p>Key persistence notes:</p>
 * <ul>
 *   <li>Composite uniqueness across (student, course, term, section) prevents duplicate registrations.</li>
 *   <li>Foreign keys to {@code Student} and {@code Course} are lazy-loaded; prefer fetch joins or projections when needed.</li>
 *   <li>Enum status is stored as a string; renaming constants requires migration.</li>
 *   <li>Inherits identity and audit timestamps from {@code BaseEntity}.</li>
 * </ul>
 *
 * @see BaseEntity
 * @see Student
 * @see Course
 * @see EnrollmentStatus
 */
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"student", "course"})
@SuperBuilder
public class Enrollment extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_enrollments_student")
    )
    private Student student;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "course_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_enrollments_course")
    )
    private Course course;

    /** Academic term identifier (e.g., 2025-FALL). */
    @Column(name = "term", length = 20, nullable = false)
    private String term;

    /** Offering section token (e.g., A, B1, LAB-1). */
    @Column(name = "section", length = 10, nullable = false)
    private String section;

    /** Enrollment lifecycle status (e.g., ENROLLED/DROPPED/COMPLETED). */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private EnrollmentStatus status;
}
