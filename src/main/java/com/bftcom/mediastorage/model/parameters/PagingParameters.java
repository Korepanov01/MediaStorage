package com.bftcom.mediastorage.model.parameters;

public class PagingParameters {

    private int PageIndex = 0;

    private int PageSize = 10;

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex < 0 ? 1 : pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize < 1 ? 1 : (pageSize > 100 ? 100 : pageSize);
    }
}
