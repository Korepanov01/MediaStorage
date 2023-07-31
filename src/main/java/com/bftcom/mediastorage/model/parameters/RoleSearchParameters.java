package com.bftcom.mediastorage.model.parameters;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleSearchParameters extends SearchStringParameters {

    private Long userId;
}
