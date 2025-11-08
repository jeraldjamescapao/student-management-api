package com.jeraldjamescapao.studentmanagementapi.repository;

import com.jeraldjamescapao.studentmanagementapi.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link Course} entities.
 *
 * <p>This repository handles persistence and query operations for courses.
 * It leverages indexes and constraints defined in the schema:</p>
 *
 * <ul>
 *   <li>{@code uq_courses_code} — enforces course code uniqueness.</li>
 *   <li>{@code ix_courses_active} — speeds up lookups by active state.</li>
 * </ul>
 *
 * <p>All operations use {@link UUID} as the primary key type.</p>
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    // schema: uq_courses_code
    Optional<Course> findByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCase(String code);

    // schema: ix_courses_active
    Page<Course> findByActiveTrue(Pageable pageable);

    Page<Course> findByActive(boolean active, Pageable pageable);

    Page<Course> findByCodeContainingIgnoreCase(String q, Pageable pageable);
}
