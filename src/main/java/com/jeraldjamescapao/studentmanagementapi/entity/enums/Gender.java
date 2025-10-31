package com.jeraldjamescapao.studentmanagementapi.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Gender options available for a student.")
public enum Gender {
    @Schema(description = "Male gender.")
    MALE,
    @Schema(description = "Female gender.")
    FEMALE,
    @Schema(description = "Other or non-binary gender.")
    OTHER
}