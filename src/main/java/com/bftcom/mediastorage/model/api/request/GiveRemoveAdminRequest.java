package com.bftcom.mediastorage.model.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
public class GiveRemoveAdminRequest {

    @NotNull(message = "Должен быть указан id пользователя (userId)")
    private Long userId;
}
