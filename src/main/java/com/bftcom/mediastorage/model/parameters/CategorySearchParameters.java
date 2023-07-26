package com.bftcom.mediastorage.model.parameters;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategorySearchParameters extends SearchStringParameters {
    private long parentCategoryId;
}
