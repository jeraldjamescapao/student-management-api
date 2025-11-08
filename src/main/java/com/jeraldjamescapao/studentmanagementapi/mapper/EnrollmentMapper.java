package com.jeraldjamescapao.studentmanagementapi.mapper;

import com.jeraldjamescapao.studentmanagementapi.dto.enrollment.*;
import com.jeraldjamescapao.studentmanagementapi.entity.Enrollment;
import com.jeraldjamescapao.studentmanagementapi.mapper.config.MapStructConfig;
import com.jeraldjamescapao.studentmanagementapi.mapper.helper.EntityRefFactory;
import org.mapstruct.*;

import java.util.List;

/**
 * Maps between {@link Enrollment} entities and DTOs for enrollment data.
 *
 * <p>Handles relationships between students and courses. Converts
 * {@code studentId} and {@code courseId} fields from DTOs into
 * id-only entity references using {@link EntityRefFactory}.</p>
 *
 * <ul>
 *   <li>{@code EnrollmentCreateRequest → Enrollment}</li>
 *   <li>{@code EnrollmentUpdateRequest → Enrollment}</li>
 *   <li>{@code Enrollment → EnrollmentResponse}</li>
 * </ul>
 *
 * <p>System-managed fields from {@code BaseEntity}
 * — such as {@code id}, {@code createdAt}, {@code updatedAt}, and {@code deletedAt} —
 * are intentionally ignored during DTO-to-entity mapping, since they are handled by JPA.</p>
 *
 * <p>All mapping rules follow {@link MapStructConfig}.</p>
 *
 * @see EnrollmentCreateRequest
 * @see EnrollmentUpdateRequest
 * @see EnrollmentResponse
 * @see Enrollment
 * @see EntityRefFactory
 */
@Mapper(config = MapStructConfig.class, uses = { EntityRefFactory.class })
public interface EnrollmentMapper {

    // Entity -> Response DTO (flatten nested ids)
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "course.id",  target = "courseId")
    EnrollmentResponse toResponse(Enrollment entity);

    // MapStruct List mapping
    List<EnrollmentResponse> toResponseList(List<Enrollment> entities);

    // Create DTO -> new Entity (expand ids into stub references)
    @Mapping(target = "student", source = "studentId", qualifiedByName = "studentRef")
    @Mapping(target = "course",  source = "courseId",  qualifiedByName = "courseRef")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Enrollment fromCreate(EnrollmentCreateRequest request);

    // Update DTO -> update existing Entity in place (PUT-style full update)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course",  ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void update(@MappingTarget Enrollment target, EnrollmentUpdateRequest request);
}
