package com.bftcom.mediastorage.repository.hibernate;

import com.bftcom.mediastorage.model.entity.MediaType;
import com.bftcom.mediastorage.repository.MediaTypeRepository;

public class HibernateMediaTypeRepository extends HibernateCrudRepository<MediaType> implements MediaTypeRepository {

    @Override
    protected Class<MediaType> getEntityClass() {
        return MediaType.class;
    }
}
