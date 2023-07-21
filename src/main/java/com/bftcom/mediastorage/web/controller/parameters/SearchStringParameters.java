package com.bftcom.mediastorage.web.controller.parameters;

public class SearchStringParameters extends PagingParameters {

    private String searchString;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
