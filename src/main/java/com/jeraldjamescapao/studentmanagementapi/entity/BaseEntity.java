package com.jeraldjamescapao.studentmanagementapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@Schema(description = "Base class containing ID and audit timestamps.")
public class BaseEntity {

    @Id
    @GeneratedValue
    @Schema(description = "Unique identifier of the entity.")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @CreationTimestamp
    @Schema(description = "Date and time when this record was created.")
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Schema(description = "Date and time when this record was last updated.")
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}
