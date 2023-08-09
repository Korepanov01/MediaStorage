class Category {
    constructor() {
        this.id = null;
        this.name = "";
        this.parentCategoryId = null;
    }
}

export const CategoryBuilder = {
    getDefault: () => new Category(),

    buildByParams: (id, name, parentCategoryId) => {
        let category = new Category();
        category.id = id;
        category.name = name;
        category.parentCategoryId = parentCategoryId;
        return category;
    },

    buildByData: (data) => {
        return CategoryBuilder.buildByParams(
            data.id,
            data.name,
            data.parentCategoryId
        )
    }
}