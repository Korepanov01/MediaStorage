package com.bftcom.mediastorage.model.parameters;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MediaSearchParameters extends SearchStringParameters {
    private List<Long> tagIds;
    private Long categoryId;
}
