package com.jeraldjamescapao.studentmanagementapi.entity;

import com.jeraldjamescapao.studentmanagementapi.entity.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "Represents a student in the school system.")
public class Student extends BaseEntity {

    @Schema(description = "Student's first name.")
    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Schema(description = "Student's last name.")
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Schema(description = "Email address of the student.")
    @Column(name = "email", length = 320, nullable = false)
    private String email;

    @Schema(description = "Gender of the student.")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10, nullable = false)
    private Gender gender;

    @Schema(description = "Date of birth.")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Schema(description = "Current enrollment status of the student.")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private StudentStatus status;
}
