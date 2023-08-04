import {PagingParameters} from "./PagingParameters";

export class SearchStringParameters extends PagingParameters {
    constructor(pageSize = null) {
        super(pageSize);
        this.searchString = null;
    }
}