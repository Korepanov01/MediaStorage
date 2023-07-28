package com.bftcom.mediastorage.service.interfaces;

import com.bftcom.mediastorage.exception.EntityAlreadyExistsException;
import com.bftcom.mediastorage.exception.EntityNotFoundException;
import com.bftcom.mediastorage.model.entity.BaseEntity;
import lombok.NonNull;

public interface SaveDeleteService<Entity extends BaseEntity> {

    void save(@NonNull Entity entity) throws EntityAlreadyExistsException;

    void delete(@NonNull Long id) throws EntityNotFoundException;
}
