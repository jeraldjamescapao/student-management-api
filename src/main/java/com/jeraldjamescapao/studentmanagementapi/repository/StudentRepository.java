package com.jeraldjamescapao.studentmanagementapi.repository;

import com.jeraldjamescapao.studentmanagementapi.entity.Student;
import com.jeraldjamescapao.studentmanagementapi.entity.enums.StudentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link Student} entities.
 *
 * <p>This component provides database access methods related to students,
 * supporting both basic CRUD operations and commonly used queries.</p>
 *
 * <p>Queries are aligned with the database schema:
 * <ul>
 *   <li>{@code uq_students_email} — ensures email uniqueness.</li>
 *   <li>{@code ix_students_status} — optimizes lookups by status.</li>
 *   <li>{@code ix_students_last_first} — improves searches by last name.</li>
 * </ul></p>
 *
 * <p>All operations use {@link UUID} as the primary key type.</p>
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    // schema: uq_students_email
    Optional<Student> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

    // Check if there’s already another student with the same email,
    // but not the one with this specific id.
    boolean existsByEmailIgnoreCaseAndIdNot(String email, UUID id);

    // schema: ix_students_status
    Page<Student> findByStatus(StudentStatus status, Pageable pageable);

    // schema: ix_students_last_first
    Page<Student> findByLastNameStartsWithIgnoreCase(String lastNamePrefix, Pageable pageable);

    // partial on last name
    Page<Student> findByLastNameContainingIgnoreCase(String q, Pageable pageable);

    // partial on first name
    Page<Student> findByFirstNameContainingIgnoreCase(String q, Pageable pageable);

    // simple all-fields OR search (first, last, email)
    Page<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String first, String last, String email, Pageable pageable
    );
}
