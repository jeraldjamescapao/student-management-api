package com.jeraldjamescapao.studentmanagementapi.mapper.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * Centralized MapStruct configuration shared across all mapper interfaces.
 *
 * <p>This interface defines common behaviors and conventions for every mapper
 * in the system. It ensures that all mapping components:</p>
 *
 * <ul>
 *   <li>Are registered as Spring beans via {@code componentModel = "spring"}.</li>
 *   <li>Use constructor injection for dependency consistency.</li>
 *   <li>Fail the build if a target property is not explicitly mapped, to avoid silent data loss.</li>
 * </ul>
 *
 * <p>Each mapper simply references this config using
 * {@code @Mapper(config = MapStructConfig.class)}.</p>
 *
 * <p>By centralizing these rules, the codebase remains consistent, predictable,
 * and easier to maintain as new mappers are introduced.</p>
 *
 * @see org.mapstruct.Mapper
 * @see org.mapstruct.MapperConfig
 * @see org.mapstruct.ReportingPolicy
 */
@MapperConfig(
        componentModel = "spring", // allow Spring to @Inject mappers
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR // build fails if we forget a field
)
public interface MapStructConfig { }
