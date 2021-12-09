package at.fh.winb.swd.libary.dto.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

public class PagedResultDTO<T> {
    @SuppressWarnings("rawtypes")
    private static final PagedResultDTO EMPTY_PAGED_RESULT = singlePage(Collections.emptyList());

    private List<T> elements;
    private long totalCount;

    /**
     * Empty constructor for deserialization.
     */
    public PagedResultDTO() {
    }

    /**
     * Constructor.
     *
     * @param elements   elements of the current page
     * @param totalCount total count of elements
     */
    public PagedResultDTO(List<T> elements, long totalCount) {
        this.elements = elements;
        this.totalCount = totalCount;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * Creates a paged result with the count of all available elements.
     *
     * @param elements   elements of the current page
     * @param totalCount total number of all available elements
     * @param <T>        type of elements
     * @return a new paged result instance
     */
    public static <T> PagedResultDTO<T> of(final List<T> elements, final long totalCount) {
        return new PagedResultDTO<>(elements, Math.max(elements.size(), totalCount));
    }

    /**
     * Creates a paged result containing all results in a single page.
     *
     * @param elements elements of the current page
     * @param <T>      type of elements
     * @return a new paged result instance
     */
    public static <T> PagedResultDTO<T> singlePage(final List<T> elements) {
        return of(elements, elements.size());
    }

    /**
     * Returns an empty paged result.
     *
     * @param <T> type of elements
     * @return empty paged result singleton instance
     */
    @SuppressWarnings("unchecked")
    public static <T> PagedResultDTO<T> empty() {
        return EMPTY_PAGED_RESULT;
    }

    public static <T> Collector<T, ArrayList<T>, PagedResultDTO<T>> toPagedResultDTO(final long totalCount) {
        return Collector.of(
                ArrayList::new,
                List::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                elements -> PagedResultDTO.of(elements, totalCount)
        );
    }
}
