import {SearchStringParameters} from "./SearchStringParameters";

export class TagSearchParameters extends SearchStringParameters {
    constructor(pageSize = null) {
        super(pageSize);
        this.mediaId = null;
    }
}
