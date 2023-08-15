import {Api} from "./Api"
import {TagBuilder} from "../models/Tag";
import {deleteRequest, getRequest, postRequest, putRequest} from "./baseApi";

export const getTags = (params) => getRequest('/tags', params);

export const deleteTag = (tagId) => deleteRequest(`/tags/${tagId}`);

export const putTag = (tagId, payload) => putRequest(`/tags/${tagId}`, payload);

export const postTag = (payload) => postRequest('/tags', payload);

export const TagAPI = {
    getAllByMedia: async function (mediaId) {
        let searchParameters = {pageIndex: 0, pageSize: 1000, mediaId: mediaId}
        return TagBuilder.buildArrayByData((await Api.get("/tags", {params: searchParameters})).data);
    },

    get: async function (searchParameters) {
        return TagBuilder.buildArrayByData((await Api.get("/tags", {params: searchParameters})).data);
    },

    getById: async function (id) {
        return TagBuilder.buildByData((await Api.get(`/tags/${id}`)).data);
    },

    delete: function (id) {
        return  Api.delete(`/tags/${id}`);
    }
}
