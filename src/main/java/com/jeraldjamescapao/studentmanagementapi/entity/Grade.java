package com.jeraldjamescapao.studentmanagementapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "Represents the grade a student receives for an enrollment.")
public class Grade extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_grades_enrollment"))
    @Schema(description = "The enrollment record this grade belongs to.")
    private Enrollment enrollment;

    @Schema(description = "Letter grade (e.g., A, B+, F).")
    @Column(name = "letter", length = 2, nullable = false)
    private String letter;

    @Schema(description = "Numeric grade points (e.g., 4.0).")
    @Column(name = "points", precision = 3, scale = 2, nullable = false)
    private BigDecimal points;

    @Schema(description = "Date and time when the grade was given.")
    @Column(name = "graded_at", nullable = false)
    private OffsetDateTime gradedAt;

    @Schema(description = "Optional notes about the grade.")
    @Lob
    @Column(name = "notes")
    private String notes;
}
