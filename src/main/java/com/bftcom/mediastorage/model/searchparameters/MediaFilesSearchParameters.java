package com.bftcom.mediastorage.model.searchparameters;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MediaFilesSearchParameters extends PagingParameters {

    private String type;

    private Long mediaId;
}
