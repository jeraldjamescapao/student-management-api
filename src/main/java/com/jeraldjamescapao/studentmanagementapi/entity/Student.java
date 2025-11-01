package com.jeraldjamescapao.studentmanagementapi.entity;

import com.jeraldjamescapao.studentmanagementapi.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * JPA entity representing a student in the school domain.
 *
 * <p>Not directly exposed via the public API. Access is managed through controllers, DTOs, mappers,
 * and services that enforce business rules.</p>
 *
 * <p>Key persistence notes:</p>
 * <ul>
 *   <li>Email is globally unique (enforced by a DB constraint).</li>
 *   <li>Common query paths are optimized with indexes on (lastName, firstName) and status.</li>
 *   <li>Enums are stored as strings; renaming enum constants requires a data migration.</li>
 *   <li>Inherits identity and audit timestamps from {@code BaseEntity}.</li>
 * </ul>
 *
 * @see BaseEntity
 * @see Gender
 * @see StudentStatus
 */
@Entity
@Table(
        name = "students",
        uniqueConstraints = @UniqueConstraint(name = "uq_students_email", columnNames = "email"),
        indexes = {
                @Index(name = "ix_students_last_first", columnList = "last_name,first_name"),
                @Index(name = "ix_students_status", columnList = "status")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class Student extends BaseEntity {

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    /** Unique email for account identity; normalize (trim/lowercase) at the service layer. */
    @Column(name = "email", length = 320, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10, nullable = false)
    private Gender gender;

    /** Date of birth (date-only, no timezone); used for age/eligibility rules. */
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    /** Student lifecycle status (e.g., APPLIED, ENROLLED, SUSPENDED, GRADUATED). */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private StudentStatus status;
}
