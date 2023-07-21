package com.bftcom.mediastorage.web.controller.parameters;

public class PagingParameters {

    private int PageIndex;

    private int PageSize;

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }
}
