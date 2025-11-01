package com.jeraldjamescapao.studentmanagementapi.entity.enums;

/**
 * Enumeration representing the current status of a student's enrollment in a course.
 *
 * <p>Each value reflects a distinct stage in the enrollment lifecycle, from initial registration
 * through completion or cancellation.</p>
 *
 * <p>Persistence note: when stored via {@code @Enumerated(EnumType.STRING)}, renaming constants
 * requires a data migration.</p>
 *
 * @see com.jeraldjamescapao.studentmanagementapi.entity.Enrollment
 */
public enum EnrollmentStatus {
    /** Student has registered but not yet officially enrolled. */
    REGISTERED,
    /** Student is currently enrolled in the course. */
    ENROLLED,
    /** Student is waitlisted pending available space. */
    WAITLISTED,
    /** Student dropped the course before the withdrawal deadline. */
    DROPPED,
    /** Student withdrew after partial completion. */
    WITHDRAWN,
    /** Course successfully completed with a final grade. */
    COMPLETED,
    /** Course failed with a non-passing grade. */
    FAILED,
    /** Course remains incomplete; pending completion requirements. */
    INCOMPLETE,
    /** Course cancelled or voided by the school administration. */
    CANCELLED
}