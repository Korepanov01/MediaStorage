package com.bftcom.mediastorage.repository.hibernate;

import com.bftcom.mediastorage.model.entity.File;
import com.bftcom.mediastorage.repository.FileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateFileRepository extends HibernateCrudRepository<File> implements FileRepository {

    @Override
    protected Class<File> getEntityClass() {
        return File.class;
    }
}
