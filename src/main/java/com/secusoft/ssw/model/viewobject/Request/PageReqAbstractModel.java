package com.secusoft.ssw.model.viewobject.Request;

/**
 * 分页查询参数抽取
 */
public class PageReqAbstractModel {

    private Integer currentPage;

    private Integer pageSize;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}


