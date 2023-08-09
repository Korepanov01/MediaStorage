package com.bftcom.mediastorage.model.api.request;

import com.bftcom.mediastorage.model.entity.Media;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostMediaRequest extends PostPutEntityRequest<Media> {

    @NotNull(message = "Должен быть указан id пользователя (userId)")
    private Long userId;

    @NotNull(message = "Должен быть указан id категории (categoryId)")
    private Long categoryId;

    @NotBlank(message = "Должно быть указано название (name)")
    @Size(max = 200, message = "Название не должно быть больше 200 символов")
    private String name;

    @Size(max = 10_000, message = "Описание не должно быть больше 10.000 символов")
    private String description;

    private Long mediaTypeId;

    @Override
    public Media covertToEntity() {
        LocalDateTime now = LocalDateTime.now();
        return new Media(userId, categoryId, name, description, mediaTypeId, now, now);
    }
}
