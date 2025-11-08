package com.jeraldjamescapao.studentmanagementapi.mapper.helper;

import com.jeraldjamescapao.studentmanagementapi.entity.*;
import org.mapstruct.Named;

import java.util.UUID;

/**
 * Utility class that provides factory methods to build lightweight
 * entity references (id-only instances) for use within MapStruct mappers.
 *
 * <p>This avoids unnecessary database lookups when converting DTOs that
 * contain only foreign key identifiers (e.g. {@code studentId},
 * {@code courseId}, {@code enrollmentId}).</p>
 *
 * <p>Each method creates a detached entity with only its {@code id} set,
 * allowing JPA to recognize relationships when persisting.</p>
 *
 * <p>Used with {@code qualifiedByName} in mappers and exposed via
 * {@code @Named} so MapStruct can find them.</p>
 */
public final class EntityRefFactory {

    @Named("studentRef")
    public static Student studentRef(UUID id) {
        if (id == null) return null;
        Student s = new Student();
        s.setId(id);
        return s;
    }

    @Named("courseRef")
    public static Course courseRef(UUID id) {
        if (id == null) return null;
        Course c = new Course();
        c.setId(id);
        return c;
    }

    @Named("enrollmentRef")
    public static Enrollment enrollmentRef(UUID id) {
        if (id == null) return null;
        Enrollment e = new Enrollment();
        e.setId(id);
        return e;
    }
}
