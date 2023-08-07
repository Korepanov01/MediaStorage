package com.bftcom.mediastorage.model.searchparameters;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MediaSearchParameters extends SearchStringParameters {
    private List<Long> tagIds;
    private Long categoryId;
    private Long userId;
    private List<Long> typeIds;
    private Boolean randomOrder;
}
