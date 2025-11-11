package com.jeraldjamescapao.studentmanagementapi.controller;

import com.jeraldjamescapao.studentmanagementapi.dto.common.PageDto;
import com.jeraldjamescapao.studentmanagementapi.dto.student.*;
import com.jeraldjamescapao.studentmanagementapi.entity.enums.StudentStatus;
import com.jeraldjamescapao.studentmanagementapi.service.StudentService;
import com.jeraldjamescapao.studentmanagementapi.web.GlobalExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller that exposes API endpoints for managing {@code Student} records.
 *
 * <p><b>Usage:</b> Handles incoming HTTP requests for listing, creating,
 * updating, deleting, and changing the status of students. Delegates all
 * operations to {@link StudentService} to keep controllers thin and focused
 * on request–response mapping.</p>
 *
 * <p><b>Base Path:</b> {@code /api/v1/students}</p>
 *
 * <p><b>Swagger:</b> Annotated with
 * {@link io.swagger.v3.oas.annotations.Operation @Operation} and
 * {@link io.swagger.v3.oas.annotations.tags.Tag @Tag} for clear
 * documentation in the generated OpenAPI interface.</p>
 *
 * @see StudentService
 * @see StudentResponse
 * @see GlobalExceptionHandler
 */
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Tag(name = "Students", description = "Operations for managing student records (v1.0)")
public class StudentController {

    private final StudentService service;

    /**
     * Retrieves a paginated list of students.
     *
     * <p><b>Parameters:</b></p>
     * <ul>
     *   <li>{@code q} – optional free-text search (first name, last name, or email)</li>
     *   <li>{@code pageable} – pagination configuration (page, size, sort)</li>
     * </ul>
     *
     * <p><b>Returns:</b> A {@link PageDto} of {@link StudentResponse} objects with
     * pagination metadata and applied sorting.</p>
     */
    @GetMapping
    @Operation(summary = "List students", description = "Returns a pageable list of students with optional free-text search.")
    public PageDto<StudentResponse> search(
            @RequestParam(required = false) String q,
            @ParameterObject Pageable pageable) {
        return PageDto.from(service.search(q, pageable));
    }

    /**
     * Retrieves a single student by ID.
     *
     * @param id the unique student ID
     * @return the corresponding {@link StudentResponse}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID", description = "Fetch a single student record by its unique identifier.")
    public StudentResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    /**
     * Creates a new student record.
     *
     * <p><b>Validation:</b> Uses Jakarta Bean Validation annotations
     * (e.g., {@code @NotBlank}, {@code @Email}) defined in
     * {@link StudentCreateRequest}.</p>
     *
     * @param req the student data to create
     * @return the newly created {@link StudentResponse}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create student", description = "Creates a new student record after validating input data.")
    public StudentResponse create(@Valid @RequestBody StudentCreateRequest req) {
        return service.create(req);
    }

    /**
     * Updates an existing student record.
     *
     * <p><b>Validation:</b> Uses {@link StudentUpdateRequest} to
     * allow partial field updates. Email uniqueness is enforced.</p>
     *
     * @param id the student ID to update
     * @param req the fields to modify
     * @return the updated {@link StudentResponse}
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update student", description = "Updates an existing student record.")
    public StudentResponse update(@PathVariable UUID id, @Valid @RequestBody StudentUpdateRequest req) {
        return service.update(id, req);
    }

    /**
     * Deletes a student record.
     *
     * <p><b>Behavior:</b> Deletion is blocked if the student has
     * existing enrollments. Otherwise, the record is permanently removed.</p>
     *
     * @param id the student ID to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete student", description = "Deletes a student record if no linked enrollments exist.")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    /**
     * Changes the current {@link StudentStatus} of a student.
     *
     * <p><b>Example:</b> {@code PATCH /api/v1/students/{id}/status?status=ACTIVE}</p>
     *
     * @param id the student ID to update
     * @param status the new {@link StudentStatus} value
     * @return the updated {@link StudentResponse}
     */
    @PatchMapping("/{id}/status")
    @Operation(summary = "Change student status", description = "Updates the current enrollment status of a student.")
    public StudentResponse changeStatus(@PathVariable UUID id,
                                        @RequestParam(name = "status") StudentStatus status) {
        return service.changeStatus(id, status);
    }
}
