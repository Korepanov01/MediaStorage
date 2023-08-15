import { Api } from "./api"
import {MediaFileAPI} from "./mediaFileAPI";
import {FileTypes} from "../enums/fileTypes";
import {Defaults} from "../enums/defaults";

export const FileAPI = {
    get: function (id) {
        return Api.get(`/files/${id}`);
    },

    post: function (file, params) {
        const formData = new FormData();
        formData.append('file', file);
        Api.post('/files', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            },
            params: params
        })

        return Api.postForm(`/files`, file, {params: params});
    },

    delete: function (id) {
        return Api.delete(`/files/${id}`);
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
