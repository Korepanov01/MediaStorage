package com.bftcom.mediastorage.model.request;

import com.bftcom.mediastorage.model.entity.BaseEntity;

public interface ToEntityConvertable<Entity extends BaseEntity> {

    Entity covertToEntity();
}
