package com.jeraldjamescapao.studentmanagementapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * JPA entity capturing a grade issued for a specific {@code Enrollment}.
 *
 * <p>Not directly exposed via the public API. Access is managed through controllers, DTOs, mappers,
 * and services that enforce business rules.</p>
 *
 * <p>Key persistence notes:</p>
 * <ul>
 *   <li>Lazy reference to {@code Enrollment}; access within a transactional context or map to DTOs.</li>
 *   <li>Indexed by {@code enrollment} (for joins) and by {@code letter} (for reporting).</li>
 *   <li>Consider service-level invariants if only one final grade per enrollment is allowed.</li>
 *   <li>Inherits identity and audit timestamps from {@code BaseEntity}.</li>
 * </ul>
 *
 * @see BaseEntity
 * @see Enrollment
 */
@Entity
@Table(
        name = "grades",
        indexes = {
                @Index(name = "ix_grades_enrollment", columnList = "enrollment_id"),
                @Index(name = "ix_grades_letter", columnList = "letter")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"enrollment"})
@SuperBuilder
public class Grade extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_grades_enrollment"))
    private Enrollment enrollment;

    /** Letter grade (e.g., A, B+); max length 2. */
    @Column(name = "letter", length = 2, nullable = false)
    private String letter;

    /** Grade points (e.g., 0.00–6.00); policy-defined scale with two decimals. */
    @Column(name = "points", precision = 3, scale = 2, nullable = false)
    private BigDecimal points;

    /** Timestamp when the grade was awarded; set by the grading workflow/service. */
    @Column(name = "graded_at", nullable = false)
    private OffsetDateTime gradedAt;

    /** Optional instructor remarks or justification. */
    @Lob
    @Column(name = "notes")
    private String notes;
}
