import { Api } from "./api"
import {MediaFileBuilder} from "../models/MediaFile";

export const MediaFileAPI = {
    get: async function (searchParameters) {
        return MediaFileBuilder.buildArrayByData((await Api.get("/media_file", {params: searchParameters})).data);
    },

    getByMediaId: async function (id) {
        return MediaFileBuilder.buildArrayByData((await Api.get("/media_file", {params: {pageIndex: 0, pageSize: 100, mediaId: id}})).data);
    }
}
