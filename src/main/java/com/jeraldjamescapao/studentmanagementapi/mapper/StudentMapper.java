package com.jeraldjamescapao.studentmanagementapi.mapper;

import com.jeraldjamescapao.studentmanagementapi.dto.student.*;
import com.jeraldjamescapao.studentmanagementapi.entity.Student;
import com.jeraldjamescapao.studentmanagementapi.mapper.config.MapStructConfig;
import org.mapstruct.*;

import java.util.List;

/**
 * Maps between {@link Student} entities and their corresponding DTOs.
 *
 * <p>Used to translate data between the persistence layer (JPA entity)
 * and the API layer (request/response DTOs) for student records.</p>
 *
 * <ul>
 *   <li>{@code StudentCreateRequest → Student}</li>
 *   <li>{@code StudentUpdateRequest → Student}</li>
 *   <li>{@code Student → StudentResponse}</li>
 * </ul>
 *
 * <p>System-managed fields from {@code BaseEntity}
 * — such as {@code id}, {@code createdAt}, {@code updatedAt}, and {@code deletedAt} —
 * are intentionally ignored during DTO-to-entity mapping, since they are handled by JPA.</p>
 *
 * <p>All mappings follow the global rules defined in {@link MapStructConfig}.</p>
 *
 * @see StudentCreateRequest
 * @see StudentUpdateRequest
 * @see StudentResponse
 * @see Student
 */
@Mapper(config = MapStructConfig.class)
public interface StudentMapper {

    // Entity -> Response DTO
    StudentResponse toResponse(Student entity);

    // MapStruct List mapping
    List<StudentResponse> toResponseList(List<Student> entities);

    // Create DTO -> new Entity (ignore BaseEntity fields)

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Student fromCreate(StudentCreateRequest request);

    // Update DTO -> update existing Entity in place and ignore BaseEntity fields (PUT-style full update)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void update(@MappingTarget Student target, StudentUpdateRequest request);
}
