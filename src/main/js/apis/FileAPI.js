import { Api } from "./Api"
import {MediaFileAPI} from "./MediaFileAPI";
import {FileTypes} from "../enums/FileTypes";
import {defaults} from "axios";
import {Defaults} from "../enums/Defaults";

export const FileAPI = {
    get: function (id) {
        return Api.get(`/files/${id}`);
    },

    getThumbnailUrl: async function (mediaId) {
        let searchParameters = {pageIndex: 0, pageSize: 1, mediaId: mediaId, type: FileTypes.thumbnail};
        let mediaFiles = await MediaFileAPI.get(searchParameters);
        if (mediaFiles.length === 0)
            return Defaults.defaultImageUrl;

        return mediaFiles[0].url;
    },

    getMainUrls: async function (mediaId) {
        let searchParameters = {pageIndex: 0, pageSize: 100, mediaId: mediaId, type: FileTypes.main};
        let mediaFiles = await MediaFileAPI.get(searchParameters);
        if (mediaFiles.length === 0)
            return  [Defaults.defaultImageUrl];

        return mediaFiles.map(mediaFile => mediaFile.url);
    }
}
