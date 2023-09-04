package com.bftcom.mediastorage.web.model.parameter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchStringParameters extends PagingParameters {
    private String searchString;

    public void setSearchString(String searchString) {
        this.searchString = StringUtils.hasText(searchString) ? searchString : null;
    }
}
