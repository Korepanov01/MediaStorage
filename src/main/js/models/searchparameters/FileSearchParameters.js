import {SearchStringParameters} from "./SearchStringParameters";

export class FileSearchParameters extends SearchStringParameters {
    constructor(pageSize = null) {
        super(pageSize);
        this.type = null;
        this.mediaId = null;
    }
}
