package com.bftcom.mediastorage.repository.hibernate;

import com.bftcom.mediastorage.model.entity.Category;
import com.bftcom.mediastorage.repository.CategoryRepository;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateCategoryRepository extends HibernateCrudRepository<Category> implements CategoryRepository {

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }

    @Override
    public List<Category> findByParentCategoryId(@NonNull Long parentCategoryId) {
        return this.new ParametersSearcher()
                .select()
                .where()
                .addEqualsCondition("parentCategory.id", parentCategoryId)
                .find();
    }
}
