package com.bftcom.mediastorage.repository.hibernate;

import com.bftcom.mediastorage.model.entity.FileType;
import com.bftcom.mediastorage.repository.FileTypeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateFileTypeRepository extends HibernateCrudRepository<FileType> implements FileTypeRepository {

    @Override
    protected Class<FileType> getEntityClass() {
        return FileType.class;
    }
}
