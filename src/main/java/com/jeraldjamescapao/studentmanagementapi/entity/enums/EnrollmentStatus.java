package com.jeraldjamescapao.studentmanagementapi.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Status of a student's enrollment in a specific course.")
public enum EnrollmentStatus {
    @Schema(description = "Student has registered but not yet enrolled.")
    REGISTERED,
    @Schema(description = "Student is officially enrolled in the course.")
    ENROLLED,
    @Schema(description = "Student is waitlisted for a spot in the course.")
    WAITLISTED,
    @Schema(description = "Student dropped the course before the deadline.")
    DROPPED,
    @Schema(description = "Student withdrew after partial completion.")
    WITHDRAWN,
    @Schema(description = "Course successfully completed with a final grade.")
    COMPLETED,
    @Schema(description = "Course failed with a non-passing grade.")
    FAILED,
    @Schema(description = "Course incomplete; pending completion requirements.")
    INCOMPLETE,
    @Schema(description = "Course cancelled or voided by the school system.")
    CANCELLED
}
