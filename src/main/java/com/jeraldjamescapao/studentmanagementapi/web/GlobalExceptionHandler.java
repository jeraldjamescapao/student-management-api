package com.jeraldjamescapao.studentmanagementapi.web;

import com.jeraldjamescapao.studentmanagementapi.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * Centralized exception handler for all REST controllers.
 *
 * <p><b>Usage:</b> Captures application-specific exceptions and converts
 * them into consistent {@link ErrorResponse} payloads. This ensures
 * predictable error formatting for all API clients.</p>
 *
 * <p><b>Behavior:</b> Each handler method maps a specific exception type
 * to an appropriate HTTP status code and message:</p>
 * <ul>
 *   <li>{@link NotFoundException} → 404 Not Found</li>
 *   <li>{@link ConflictException} → 409 Conflict</li>
 *   <li>{@link BadRequestException} → 400 Bad Request</li>
 *   <li>{@link MethodArgumentNotValidException} → 400 Bad Request (validation errors)</li>
 * </ul>
 *
 * <p><b>Note:</b> This class is annotated with
 * {@link RestControllerAdvice},
 * which allows it to handle exceptions across all controllers globally.</p>
 *
 * @see ErrorResponse
 * @see NotFoundException
 * @see ConflictException
 * @see BadRequestException
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Map NotFoundException to 404.
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(NotFoundException ex, HttpServletRequest req) {
        return ErrorResponse.of(404, "Not Found", ex.getMessage(), req.getRequestURI());
    }

    /**
     * Map ConflictException to 409.
     */
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflict(ConflictException ex, HttpServletRequest req) {
        return ErrorResponse.of(409, "Conflict", ex.getMessage(), req.getRequestURI());
    }

    /**
     * Map BadRequestException to 400.
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(BadRequestException ex, HttpServletRequest req) {
        return ErrorResponse.of(400, "Bad Request", ex.getMessage(), req.getRequestURI());
    }

    /**
     * Map bean validation errors to 400.
     * Report the first field error.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .findFirst().map(fe -> fe.getField() + " " + fe.getDefaultMessage())
                .orElse("Validation failed");
        return ErrorResponse.of(400, "Bad Request", msg, req.getRequestURI());
    }
}
