package at.fh.winb.swd.libary.searchRequest.base;

import java.util.List;

public class SearchRequest {

    /**
     * The default page size.
     */
    public static final int DEFAULT_PAGE_SIZE = 100;

    private int page;
    private int pageSize;
    private List<String> order;

    public SearchRequest() {
    }

    public SearchRequest(int page, int pageSize, List<String> order) {
        this.page = page;
        this.pageSize = pageSize;
        this.order = order;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getOrder() {
        return order;
    }

    public void setOrder(List<String> order) {
        this.order = order;
    }
}
