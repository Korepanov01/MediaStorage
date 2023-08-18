package com.bftcom.mediastorage.model.searchparameters;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

@Data
@EqualsAndHashCode
public class CategorySearchParameters {
    private String searchString;
    @Required(message = "Должна быть указана родительская категория")
    private Long parentCategoryId;
}
