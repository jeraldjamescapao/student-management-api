package com.jeraldjamescapao.studentmanagementapi.dto.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * DTO used for returning paginated API responses.
 *
 * <p><b>Purpose:</b> Provides a consistent and predictable JSON structure
 * when exposing {@code Page<T>} results to API consumers.</p>
 *
 * <p><b>Structure:</b> Contains both the list of items and pagination metadata
 * such as current page index, total pages, and sorting order.</p>
 *
 * <p><b>Usage:</b> Convert any {@link Page} using {@link #from(Page)} before
 * returning it from controller methods.</p>
 *
 * @param <T> the element type of the paginated content
 * @param content items in the current page
 * @param page zero-based page index
 * @param size number of items per page
 * @param totalElements total items across all pages
 * @param totalPages total number of pages
 * @param first whether this is the first page
 * @param last whether this is the last page
 * @param sort applied sort tokens (e.g., {@code ["lastName,asc"]})
 */
public record PageDto<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        List<String> sort
) {

    /**
     * Converts a {@link Page} into a {@link PageDto}.
     *
     * <p><b>Usage:</b> Used in controllers to wrap paginated data
     * before returning it to the client.</p>
     *
     * @param p the {@link Page} instance to convert
     * @return a {@link PageDto} representing the same pagination details
     */
    public static <T> PageDto<T> from(Page<T> p) {
        List<String> sortSpec = p.getSort().stream()
                .map(PageDto::formatOrder)
                .toList();
        return new PageDto<>(
                p.getContent(),
                p.getNumber(),
                p.getSize(),
                p.getTotalElements(),
                p.getTotalPages(),
                p.isFirst(),
                p.isLast(),
                sortSpec
        );
    }

    private static String formatOrder(Sort.Order o) {
        return o.getProperty() + "," + o.getDirection().name().toLowerCase();
    }
}
