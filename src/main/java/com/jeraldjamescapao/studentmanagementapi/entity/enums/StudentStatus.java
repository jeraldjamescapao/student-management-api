package com.jeraldjamescapao.studentmanagementapi.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Lifecycle status of a student in the school system.")
public enum StudentStatus {
    @Schema(description = "Applied but not yet admitted.")
    APPLIED,
    @Schema(description = "Admitted but not yet enrolled.")
    ADMITTED,
    @Schema(description = "Currently enrolled in active courses.")
    ENROLLED,
    @Schema(description = "Temporarily on academic leave.")
    ON_LEAVE,
    @Schema(description = "Suspended for disciplinary or academic reasons.")
    SUSPENDED,
    @Schema(description = "Formally withdrawn from the school system.")
    WITHDRAWN,
    @Schema(description = "Successfully graduated.")
    GRADUATED,
    @Schema(description = "No longer active in any capacity.")
    INACTIVE
}
