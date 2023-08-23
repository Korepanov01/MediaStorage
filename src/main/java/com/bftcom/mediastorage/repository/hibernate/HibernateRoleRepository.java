package com.bftcom.mediastorage.repository.hibernate;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.repository.RoleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateRoleRepository extends HibernateCrudRepository<Role> implements RoleRepository {

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}
