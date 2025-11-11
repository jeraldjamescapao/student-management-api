package com.jeraldjamescapao.studentmanagementapi.service.impl;

import com.jeraldjamescapao.studentmanagementapi.dto.student.*;
import com.jeraldjamescapao.studentmanagementapi.entity.Student;
import com.jeraldjamescapao.studentmanagementapi.entity.enums.StudentStatus;
import com.jeraldjamescapao.studentmanagementapi.exception.*;
import com.jeraldjamescapao.studentmanagementapi.mapper.StudentMapper;
import com.jeraldjamescapao.studentmanagementapi.repository.EnrollmentRepository;
import com.jeraldjamescapao.studentmanagementapi.repository.StudentRepository;
import com.jeraldjamescapao.studentmanagementapi.service.StudentService;
import com.jeraldjamescapao.studentmanagementapi.web.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Default implementation of {@link StudentService} providing all
 * business operations for managing {@code Student} entities.
 *
 * <p><b>Responsibilities:</b> Implements core CRUD and search
 * logic, including:</p>
 * <ul>
 *   <li>Validating unique student emails during create and update operations.</li>
 *   <li>Handling pagination and free-text search queries.</li>
 *   <li>Preventing deletion of students with active enrollments.</li>
 *   <li>Mapping between entities and DTOs using {@link StudentMapper}.</li>
 * </ul>
 *
 * <p><b>Transaction Management:</b> Uses
 * {@link Transactional @Transactional}
 * for write operations, and read-only transactions for queries.</p>
 *
 * <p><b>Exception Handling:</b> Throws custom runtime exceptions
 * ({@link NotFoundException}, {@link ConflictException},
 * {@link BadRequestException}) that are mapped by
 * {@link GlobalExceptionHandler GlobalExceptionHandler}.</p>
 *
 * @see StudentService
 * @see StudentResponse
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final StudentMapper mapper;

    /**
     * Searches for students by free-text query on name or email.
     * If no query is given, returns all students.
     */
    @Override
    public Page<StudentResponse> search(String q, Pageable pageable) {
        String query = (q == null) ? null : q.trim();
        Page<Student> page = (query == null || query.isEmpty())
                ? studentRepo.findAll(pageable)
                : studentRepo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                query, query, query, pageable);
        return page.map(mapper::toResponse);
    }

    /**
     * Retrieves a student by ID or throws {@link NotFoundException}.
     */
    @Override
    public StudentResponse get(UUID id) {
        Student s = studentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Student", id));
        return mapper.toResponse(s);
    }

    /**
     * Creates a new student after validating email uniqueness.
     */
    @Override
    @Transactional
    public StudentResponse create(StudentCreateRequest req) {
        if (studentRepo.existsByEmailIgnoreCase(req.getEmail())) {
            throw new ConflictException("Email already in use: " + req.getEmail());
        }
        Student entity = mapper.fromCreate(req);
        Student saved = studentRepo.save(entity);
        return mapper.toResponse(saved);
    }

    /**
     * Updates an existing student, enforcing unique email constraint.
     */
    @Override
    @Transactional
    public StudentResponse update(UUID id, StudentUpdateRequest req) {
        Student s = studentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Student", id));

        if (req.getEmail() != null &&
                studentRepo.existsByEmailIgnoreCaseAndIdNot(req.getEmail(), id)) {
            throw new ConflictException("Email already in use: " + req.getEmail());
        }

        mapper.update(s, req);
        return mapper.toResponse(s);
    }

    /**
     * Deletes a student if no linked enrollments exist.
     */
    @Override
    @Transactional
    public void delete(UUID id) {
        Student s = studentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Student", id));

        boolean hasEnrollments = enrollmentRepo.existsByStudentId(id);
        if (hasEnrollments) {
            throw new BadRequestException("Cannot delete student with existing enrollments");
        }
        studentRepo.delete(s);
    }

    /**
     * Updates the {@link StudentStatus} of a student record.
     */
    @Override
    @Transactional
    public StudentResponse changeStatus(UUID id, StudentStatus status) {
        Student s = studentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Student", id));
        s.setStatus(status);
        return mapper.toResponse(s);
    }
}
