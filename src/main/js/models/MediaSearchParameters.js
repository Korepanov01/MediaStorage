import {SearchStringParameters} from "./SearchStringParameters";

export class MediaSearchParameters extends SearchStringParameters {
    constructor(pageSize = null) {
        super(pageSize);
        this.tagIds = null;
        this.categoryId = null;
    }
}
