package com.jeraldjamescapao.studentmanagementapi.entity.enums;

/**
 * Enumeration representing the gender options available for a student record.
 *
 * <p>Intended for administrative/reporting purposes. Applications should remain inclusive and
 * configurable for local requirements.</p>
 *
 * <p>Persistence note: when stored via {@code @Enumerated(EnumType.STRING)}, renaming constants
 * requires a data migration.</p>
 *
 * @see com.jeraldjamescapao.studentmanagementapi.entity.Student
 */
public enum Gender {
    MALE,
    FEMALE,
    /** Non-binary or other self-identified gender. */
    OTHER
}