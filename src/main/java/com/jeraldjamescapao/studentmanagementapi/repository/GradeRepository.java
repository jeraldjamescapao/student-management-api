package com.jeraldjamescapao.studentmanagementapi.repository;

import com.jeraldjamescapao.studentmanagementapi.entity.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link Grade} entities.
 *
 * <p>This repository exposes CRUD and query methods for student grades.
 * Queries correspond to schema indexes for efficient lookups:</p>
 *
 * <ul>
 *   <li>{@code ix_grades_enrollment} — for filtering grades by enrollment ID.</li>
 *   <li>{@code ix_grades_letter} — for filtering grades by letter value.</li>
 * </ul>
 *
 * <p>All operations use {@link UUID} as the primary key type.</p>
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, UUID>{

    // schema: ix_grades_enrollment
    Page<Grade> findByEnrollmentId(UUID enrollmentId, Pageable pageable);

    // latest grade entry per enrollment
    Optional<Grade> findTopByEnrollmentIdOrderByGradedAtDesc(UUID enrollmentId);

    // optional helper by letter (uses ix_grades_letter)
    Page<Grade> findByLetter(String letter, Pageable pageable);
}
