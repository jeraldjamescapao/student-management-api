package com.jeraldjamescapao.studentmanagementapi.entity.enums;

/**
 * Enumeration representing the lifecycle status of a student within the school system.
 *
 * <p>Defines key states from initial application to graduation or inactivity. Used to track
 * administrative progress and eligibility.</p>
 *
 * <p>Persistence note: when stored via {@code @Enumerated(EnumType.STRING)}, renaming constants
 * requires a data migration.</p>
 *
 * @see com.jeraldjamescapao.studentmanagementapi.entity.Student
 */
public enum StudentStatus {
    /** Applied but not yet admitted. */
    APPLIED,
    /** Admitted but not yet enrolled in any course. */
    ADMITTED,
    /** Currently enrolled in active courses. */
    ENROLLED,
    /** Temporarily on academic or personal leave. */
    ON_LEAVE,
    /** Suspended for disciplinary or academic reasons. */
    SUSPENDED,
    /** Formally withdrawn from the institution. */
    WITHDRAWN,
    /** Successfully completed all program requirements. */
    GRADUATED,
    /** No longer active in any capacity. */
    INACTIVE
}