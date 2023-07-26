package com.bftcom.mediastorage.model.parameters;

import lombok.Data;

@Data
public class PagingParameters {
    private int pageIndex = 0;
    private int pageSize = 10;

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex < 0 ? 1 : pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize < 1 ? 1 : (pageSize > 100 ? 100 : pageSize);
    }
}
