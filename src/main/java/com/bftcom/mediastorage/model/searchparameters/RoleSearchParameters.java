package com.bftcom.mediastorage.model.searchparameters;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleSearchParameters extends SearchStringParameters {

    private Long userId;
}
