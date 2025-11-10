package com.jeraldjamescapao.studentmanagementapi.repository;

import com.jeraldjamescapao.studentmanagementapi.entity.Enrollment;
import com.jeraldjamescapao.studentmanagementapi.entity.enums.EnrollmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link Enrollment} entities.
 *
 * <p>This repository defines database operations for enrollments and uses
 * keys and indexes defined in the schema:</p>
 *
 * <ul>
 *   <li>{@code uq_enrollments} — ensures one unique enrollment per
 *       (student_id, course_id, term, section).</li>
 *   <li>{@code ix_enrollments_student} — optimizes lookups by student ID.</li>
 *   <li>{@code ix_enrollments_course} — optimizes lookups by course ID.</li>
 *   <li>{@code ix_enrollments_status} — speeds up status-based filters.</li>
 * </ul>
 *
 * <p>All operations use {@link UUID} as the primary key type.</p>
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {

    // schema: uq_enrollments (student_id, course_id, term, section)
    Optional<Enrollment> findByStudentIdAndCourseIdAndTermAndSection(
        UUID studentId, UUID courseId, String term, String section
    );

    // True when a student has any enrollment.
    boolean existsByStudentId(UUID studentId);

    boolean existsByStudentIdAndCourseIdAndTermAndSection(
        UUID studentId, UUID courseId, String term, String section
    );

    // schema: ix_enrollments_student
    Page<Enrollment> findByStudentId(UUID studentId, Pageable pageable);

    // schema: ix_enrollments_course
    Page<Enrollment> findByCourseId(UUID courseId, Pageable pageable);

    // schema: ix_enrollments_status
    Page<Enrollment> findByStatus(EnrollmentStatus status, Pageable pageable);
}
