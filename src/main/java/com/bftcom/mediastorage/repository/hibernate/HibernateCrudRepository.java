package com.bftcom.mediastorage.repository.hibernate;

import com.bftcom.mediastorage.repository.CrudRepository;
import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public abstract class HibernateCrudRepository <Entity> implements CrudRepository<Entity> {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public Entity findById(@NonNull Long id) {
        return getSession().get(getEntityClass(), id);
    }

    @Transactional(readOnly = true)
    public Entity findByField(@NonNull String fieldName, @NonNull Object value) {
        return this.new ParametersSearcher()
                .selectFrom()
                .where()
                .addEqualsCondition(fieldName, value)
                .findUnique();
    }

    @Transactional(readOnly = true)
    public boolean existsByField(@NonNull String fieldName, @NonNull Object value) {
        return this.new ParametersSearcher()
                .count()
                .where()
                .addEqualsCondition(fieldName, value)
                .getNumber() > 0;
    }

    @Transactional(readOnly = true)
    public Entity findByName(@NonNull String name) {
        return findByField("name", name);
    }

    @Transactional(readOnly = true)
    public boolean existsByName(@NonNull String name) {
        return existsByField("name", name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Entity> findAll() {
        return this.new ParametersSearcher().selectFrom().find();
    }

    @Override
    @Transactional
    public void save(@NonNull Entity entity) {
        getSession().save(entity);
    }

    @Override
    @Transactional
    public void delete(@NonNull Entity entity) {
        getSession().delete(entity);
    }

    protected abstract Class<Entity> getEntityClass();

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected class ParametersSearcher {

        private final StringBuilder hqlBuilder = new StringBuilder();

        private final HashMap<String, Object> queryParams = new HashMap<>();

        public ParametersSearcher selectFrom() {
            select();
            from();
            return this;
        }

        public ParametersSearcher select() {
            hqlBuilder.append("SELECT ").append(getAlias()).append(" ");
            return this;
        }

        public ParametersSearcher from() {
            hqlBuilder.append("\nFROM ").append(getEntityClass().getSimpleName()).append(" ").append(getAlias()).append(" ");
            return this;
        }

        public ParametersSearcher count() {
            hqlBuilder.append("\nSELECT COUNT(*)");
            from();
            return this;
        }

        public ParametersSearcher where() {
            hqlBuilder.append("\nWHERE 1 = 1 ");
            return this;
        }

        public ParametersSearcher and() {
            hqlBuilder.append("AND ");
            return this;
        }

        public ParametersSearcher addStatement(@NonNull String statement, @Nullable Map<String, Object> queryParams) {
            hqlBuilder.append(statement).append(" ");
            if (queryParams != null) {
                this.queryParams.putAll(queryParams);
            }
            return this;
        }

        public ParametersSearcher addStatement(@NonNull String statement) {
            return addStatement(statement, null);
        }

        public ParametersSearcher tryAddEqualsCondition(@NonNull String fieldName, @Nullable Object param) {
            if (param != null)
                addEqualsCondition(fieldName, param);
            return this;
        }

        public ParametersSearcher addEqualsCondition(@NonNull String fieldName, @NonNull Object param) {
            and();
            String varName = getVarName(fieldName);
            hqlBuilder.append(fieldName).append(" = :").append(varName);
            queryParams.put(varName, param);
            return this;
        }

        public ParametersSearcher addSearchStringCondition(@NonNull String fieldName, @NonNull String searchString) {
            and();
            String varName = getVarName(fieldName);
            hqlBuilder
                    .append("LOWER(").append(fieldName)
                    .append(") LIKE LOWER(:").append(varName).append(")");
            queryParams.put(varName, "%" + searchString + "%");
            return this;
        }

        public ParametersSearcher tryAddSearchStringCondition(String fieldName, String searchString) {
            if (StringUtils.hasText(fieldName) && StringUtils.hasText(searchString))
                addSearchStringCondition(fieldName, searchString);
            return this;
        }

        public ParametersSearcher orderRandom() {
            hqlBuilder.append("\nORDER BY rand() ");
            return this;
        }

        public Entity findUnique() {
            try {
                return buildQuery(getEntityClass()).getSingleResult();
            }
            catch (NoResultException e) {
                return null;
            }
        }

        public Long getNumber() {
            TypedQuery<Long> query = buildQuery(Long.class);

            return query.getSingleResult();
        }

        public List<Entity> find(int pageIndex, int pageSize) {
            return buildQuery(getEntityClass())
                    .setFirstResult(pageIndex * pageSize)
                    .setMaxResults(pageSize).getResultList();
        }

        public List<Entity> find() {
            return buildQuery(getEntityClass()).getResultList();
        }

        private <T> TypedQuery<T> buildQuery(Class<T> queryType) {
            String hql = hqlBuilder.toString();
            TypedQuery<T> query = getSession().createQuery(hql, queryType);

            for (Map.Entry<String, Object> param : queryParams.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
            return query;
        }

        private char getAlias() {
            return Character.toLowerCase(getEntityClass().getSimpleName().charAt(0));
        }

        private String getVarName(@NonNull String fieldName) {
            return fieldName.replace('.', '_');
        }

    }
}
