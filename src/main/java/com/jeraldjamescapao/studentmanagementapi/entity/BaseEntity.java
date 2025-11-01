package com.jeraldjamescapao.studentmanagementapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Abstract JPA base class providing a shared primary key and audit timestamps
 * for all persistent entities in the system.
 *
 * <p>Not directly exposed via the public API. Access is managed through controllers, DTOs, mappers,
 * and services that enforce business rules.</p>
 *
 * <p>Key persistence notes:</p>
 * <ul>
 *   <li>{@code id}: Auto-generated {@link java.util.UUID} serving as the primary key for all entities.</li>
 *   <li>{@code createdAt}: Automatically populated when the record is first persisted.</li>
 *   <li>{@code updatedAt}: Automatically updated whenever the record is modified.</li>
 *   <li>{@code deletedAt}: Soft-delete marker; a non-null value indicates the record was logically deleted.</li>
 *   <li>Declared as a {@code @MappedSuperclass} so its fields are mapped into subclass tables, not a separate one.</li>
 * </ul>
 *
 * <p><strong>Equality &amp; hash code:</strong> Two entities are considered equal only if they are of the same
 * concrete type (proxy-safe comparison) and have a non-null, equal {@code id}. {@code hashCode()} returns the
 * class hash to remain stable before persistence.</p>
 *
 * @see jakarta.persistence.MappedSuperclass
 * @see org.hibernate.annotations.CreationTimestamp
 * @see org.hibernate.annotations.UpdateTimestamp
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    /** Auto-generated primary key (immutable). */
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    /** Timestamp set when the record is first persisted. */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    /** Timestamp updated on each modification. */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    /** Timestamp when the record was soft-deleted (null if active). */
    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        // Stable even before persistence.
        return getClass().hashCode();
    }
}
