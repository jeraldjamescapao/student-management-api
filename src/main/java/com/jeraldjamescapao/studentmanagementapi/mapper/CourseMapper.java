package com.jeraldjamescapao.studentmanagementapi.mapper;

import com.jeraldjamescapao.studentmanagementapi.dto.course.*;
import com.jeraldjamescapao.studentmanagementapi.entity.Course;
import com.jeraldjamescapao.studentmanagementapi.mapper.config.MapStructConfig;
import org.mapstruct.*;

import java.util.List;

/**
 * Maps between {@link Course} entities and their related DTOs.
 *
 * <p>Handles conversions for course information between the database
 * representation and the API payloads.</p>
 *
 * <ul>
 *   <li>{@code CourseCreateRequest → Course}</li>
 *   <li>{@code CourseUpdateRequest → Course}</li>
 *   <li>{@code Course → CourseResponse}</li>
 * </ul>
 *
 * <p>System-managed fields from {@code BaseEntity}
 * — such as {@code id}, {@code createdAt}, {@code updatedAt}, and {@code deletedAt} —
 * are intentionally ignored during DTO-to-entity mapping, since they are handled by JPA.</p>
 *
 * <p>Follows the shared MapStruct configuration from {@link MapStructConfig}.</p>
 *
 * @see CourseCreateRequest
 * @see CourseUpdateRequest
 * @see CourseResponse
 * @see Course
 */
@Mapper(config = MapStructConfig.class)
public interface CourseMapper {

    // Entity -> Response DTO
    CourseResponse toResponse(Course entity);

    // MapStruct List mapping
    List<CourseResponse> toResponseList(List<Course> entities);

    // Create DTO -> new Entity (allow active to be null; service may default it)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Course fromCreate(CourseCreateRequest request);

    // Update DTO -> update existing Entity in place (PUT-style full update)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void update(@MappingTarget Course target, CourseUpdateRequest request);
}
