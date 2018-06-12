package me.webapp.domain;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class PageParam {

    private int pageNo;
    private int pageSize;
    private int maxPageNo;

    public PageParam() {}

    public PageParam(int pageNo, int pageSize, int maxPageNo) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.maxPageNo = maxPageNo;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getMaxPageNo() {
        return maxPageNo;
    }
}
