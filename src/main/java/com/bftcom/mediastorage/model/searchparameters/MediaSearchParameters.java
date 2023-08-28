package com.bftcom.mediastorage.model.searchparameters;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MediaSearchParameters extends SearchStringParameters {
    private List<Long> tagIds;
    private Long categoryId;
    private Long userId;
    private List<Long> typeIds;
    private Boolean randomOrder = false;

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds != null ? tagIds : new ArrayList<>();
    }

    public void setTypeIds(List<Long> typeIds) {
        this.typeIds = typeIds != null ? typeIds : new ArrayList<>();
    }
}
