package com.jeraldjamescapao.studentmanagementapi.mapper;

import com.jeraldjamescapao.studentmanagementapi.dto.grade.*;
import com.jeraldjamescapao.studentmanagementapi.entity.Grade;
import com.jeraldjamescapao.studentmanagementapi.mapper.config.MapStructConfig;
import com.jeraldjamescapao.studentmanagementapi.mapper.helper.EntityRefFactory;
import org.mapstruct.*;

import java.util.List;

/**
 * Maps between {@link Grade} entities and DTOs used for grade records.
 *
 * <p>Handles conversions between the domain entity and its corresponding
 * creation, update, and response models. Resolves the {@code enrollmentId}
 * field via {@link EntityRefFactory}.</p>
 *
 * <ul>
 *   <li>{@code GradeCreateRequest → Grade}</li>
 *   <li>{@code GradeUpdateRequest → Grade}</li>
 *   <li>{@code Grade → GradeResponse}</li>
 * </ul>
 *
 * <p>System-managed fields from {@code BaseEntity}
 * — such as {@code id}, {@code createdAt}, {@code updatedAt}, and {@code deletedAt} —
 * are intentionally ignored during DTO-to-entity mapping, since they are handled by JPA.</p>
 *
 * <p>All settings and conventions are derived from {@link MapStructConfig}.</p>
 *
 * @see GradeCreateRequest
 * @see GradeUpdateRequest
 * @see GradeResponse
 * @see Grade
 * @see EntityRefFactory
 */
@Mapper(config = MapStructConfig.class, uses = { EntityRefFactory.class })
public interface GradeMapper {

    // Entity -> Response DTO (flatten enrollmentId)
    @Mapping(source = "enrollment.id", target = "enrollmentId")
    GradeResponse toResponse(Grade entity);

    // MapStruct List mapping
    List<GradeResponse> toResponseList(List<Grade> entities);

    // Create DTO -> new Entity (ignore BaseEntity fields and expand enrollmentId)
    @Mapping(target = "enrollment", source = "enrollmentId", qualifiedByName = "enrollmentRef")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Grade fromCreate(GradeCreateRequest request);

    // Update DTO -> update existing Entity in place and ignore BaseEntity fields (PUT-style full update)
    @Mapping(target = "enrollment", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void update(@MappingTarget Grade target, GradeUpdateRequest request);
}
