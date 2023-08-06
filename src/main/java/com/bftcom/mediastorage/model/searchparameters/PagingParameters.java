package com.bftcom.mediastorage.model.searchparameters;

import lombok.Data;

@Data
public class PagingParameters {

    private static final int MIN_PAGE_SIZE = 1;
    private static final int MAX_PAGE_SIZE = 100;

    private int pageIndex = 0;
    private int pageSize = 10;

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex < 0 ? 1 : pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = Math.min(Math.max(pageSize, MIN_PAGE_SIZE), MAX_PAGE_SIZE);
    }
}
