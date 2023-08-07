import {SearchStringParameters} from "./SearchStringParameters";

export class CategorySearchParameters extends SearchStringParameters {
    constructor(pageSize = null) {
        super(pageSize);
        this.parentCategoryId = null;
    }
}
