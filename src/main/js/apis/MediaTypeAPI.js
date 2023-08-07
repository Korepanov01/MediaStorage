import { Api } from "./Api"
import {PagingParameters} from "../models/searchparameters/PagingParameters";

export const MediaTypeAPI = {
    get: function () {
        return  Api.get("/media_type", {params: new PagingParameters(100)});
    }
}
