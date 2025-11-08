package com.jeraldjamescapao.studentmanagementapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * JPA entity describing an academic course that can be offered and enrolled by students.
 *
 * <p>Not directly exposed via the public API. Access is managed through controllers, DTOs, mappers,
 * and services that enforce business rules.</p>
 *
 * <p>Key persistence notes:</p>
 * <ul>
 *   <li>Course code is unique to prevent duplicate curricula entries.</li>
 *   <li>Index on {@code active} supports fast catalog filtering.</li>
 *   <li>Use {@code active=false} to retire a course without losing historical data.</li>
 *   <li>Inherits identity and audit timestamps from {@code BaseEntity}.</li>
 * </ul>
 *
 * @see BaseEntity
 * @see Enrollment
 */
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
public class Course extends BaseEntity {

    /** Human-readable unique course code (e.g., CS101); stable external reference. */
    @Column(name = "code", length = 32, nullable = false)
    private String code;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    /** Number of academic credits per institutional policy. */
    @Column(name = "credits", nullable = false)
    private Integer credits;

    /** Soft-retire flag; set false to remove from the course catalog without deleting history. */
    @Builder.Default
    @Column(name = "active", nullable = false)
    private boolean active = true;
}
