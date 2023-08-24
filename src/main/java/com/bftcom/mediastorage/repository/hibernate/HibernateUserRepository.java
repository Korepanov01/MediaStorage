package com.bftcom.mediastorage.repository.hibernate;

import com.bftcom.mediastorage.model.entity.User;
import com.bftcom.mediastorage.model.searchparameters.SearchStringParameters;
import com.bftcom.mediastorage.repository.UserRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateUserRepository extends HibernateCrudRepository<User> implements UserRepository {

    @Override
    public List<User> findByParameters(@NonNull SearchStringParameters searchStringParameters) {
        return this.new ParametersSearcher()
                .selectFrom()
                .where()
                .tryAddSearchStringCondition("name", searchStringParameters.getSearchString())
                .find(searchStringParameters.getPageIndex(), searchStringParameters.getPageSize());
    }

    @Override
    public User findByEmail(@NonNull String email) {
        return findByField("email", email);
    }

    @Override
    public boolean existsByEmail(@NonNull String email) {
        return existsByField("email", email);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
