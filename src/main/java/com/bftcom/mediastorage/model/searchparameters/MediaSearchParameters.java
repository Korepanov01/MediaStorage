package com.bftcom.mediastorage.model.searchparameters;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class MediaSearchParameters extends SearchStringParameters {
    private Set<Long> tagIds;
    private Long categoryId;
    private Long userId;
    private Set<Long> typeIds;
    private Boolean randomOrder = false;
}
