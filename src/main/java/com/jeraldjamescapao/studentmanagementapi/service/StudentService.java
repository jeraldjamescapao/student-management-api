package com.jeraldjamescapao.studentmanagementapi.service;

import com.jeraldjamescapao.studentmanagementapi.dto.student.*;
import com.jeraldjamescapao.studentmanagementapi.entity.enums.StudentStatus;
import com.jeraldjamescapao.studentmanagementapi.exception.BadRequestException;
import com.jeraldjamescapao.studentmanagementapi.exception.ConflictException;
import com.jeraldjamescapao.studentmanagementapi.exception.NotFoundException;
import com.jeraldjamescapao.studentmanagementapi.service.impl.StudentServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Defines the core contract for all operations involving {@code Student} entities.
 *
 * <p><b>Usage:</b> This interface is implemented by
 * {@link StudentServiceImpl}
 * and is called by REST controllers to perform application-level logic
 * such as creation, updates, and lookups of students.</p>
 *
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *   <li>Handle student creation and updates while enforcing email uniqueness.</li>
 *   <li>Provide search functionality with pageable results.</li>
 *   <li>Prevent deletion when the student has linked enrollments.</li>
 *   <li>Expose status changes for managing active/inactive students.</li>
 * </ul>
 *
 * <p><b>Note:</b> Implementations of this interface should be annotated with
 * {@code @Service} and use {@code @Transactional} for proper persistence management.</p>
 *
 * @see StudentServiceImpl
 * @see StudentCreateRequest
 * @see StudentUpdateRequest
 * @see StudentResponse
 */
public interface StudentService {

    /**
     * Searches students by name or email, returning paginated results.
     *
     * @param q optional free-text query; if {@code null} or blank, returns all students
     * @param pageable pagination details (page number, size, sort)
     * @return a paginated list of {@link StudentResponse} objects
     */
    Page<StudentResponse> search(String q, Pageable pageable);

    /**
     * Retrieves a single student by its unique ID.
     *
     * @param id the UUID of the student
     * @return a {@link StudentResponse} representation of the student
     * @throws NotFoundException
     *         if no student with the given ID exists
     */
    StudentResponse get(UUID id);

    /**
     * Creates a new student record.
     *
     * <p><b>Validation:</b> Ensures that the provided email is unique
     * before persisting the record.</p>
     *
     * @param request the data used to create a new student
     * @return the created {@link StudentResponse} object
     * @throws ConflictException
     *         if the email already exists
     */
    StudentResponse create(StudentCreateRequest request);

    /**
     * Updates an existing student record.
     *
     * <p><b>Validation:</b> Enforces unique email across students.
     * Allows selective updates through {@link StudentUpdateRequest}.</p>
     *
     * @param id the student ID to update
     * @param request the data to update
     * @return the updated {@link StudentResponse}
     * @throws NotFoundException
     *         if the student does not exist
     * @throws ConflictException
     *         if the new email conflicts with another student
     */
    StudentResponse update(UUID id, StudentUpdateRequest request);

    /**
     * Deletes a student from the system.
     *
     * <p><b>Validation:</b> Prevents deletion when enrollments exist
     * for the given student.</p>
     *
     * @param id the ID of the student to delete
     * @throws NotFoundException
     *         if the student does not exist
     * @throws BadRequestException
     *         if the student has existing enrollments
     */
    void delete(UUID id);

    /**
     * Updates the current status of a student.
     *
     * @param id the UUID of the student
     * @param status the new {@link StudentStatus} to assign
     * @return the updated {@link StudentResponse}
     * @throws NotFoundException
     *         if the student does not exist
     */
    StudentResponse changeStatus(UUID id, StudentStatus status);
}
