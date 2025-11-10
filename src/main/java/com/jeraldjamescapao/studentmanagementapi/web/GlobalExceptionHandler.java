package com.jeraldjamescapao.studentmanagementapi.web;

import com.jeraldjamescapao.studentmanagementapi.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * Maps exceptions to HTTP responses.
 * Keeps error messages short.
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
