import {Api, useDelete, useGet, usePost, usePut} from "./Api"
import {TagBuilder} from "../models/Tag";

export const useGetAllTagsByMedia = (mediaId) => {
    const searchParameters = { pageIndex: 0, pageSize: 1000, mediaId: mediaId };
    return useGet("/tags", { params: searchParameters });
};

export const useGetTags = (searchParameters) => {
    return useGet("/tags", searchParameters);
};

export const useGetTagById = (id) => {
    return useGet(`/tags/${id}`);
};

export const usePostTag = (payload) => {
    return usePost(`/tags`, payload);
};

export const usePutTag = (payload) => {
    return usePut(`/tags`, payload);
};

export const useDeleteTag = (id) => {
    return useDelete(`/tags/${id}`);
};

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
