import {deleteRequest, getRequest, postRequest, putRequest} from "./baseApi";

export const getMediaById = (id) => getRequest(`/media/${id}`);

export const getMedias = (params) => getRequest('/media', params);

export const deleteMedia = (id) => deleteRequest(`/media/${id}`);

export const putMedia = (id, payload) => putRequest(`/media/${id}`, payload);

export const postMedia = (payload) => postRequest('/media', payload);

export const addTagToMedia = (mediaId, tagId) => postRequest(`/media/${mediaId}/add_tag`, undefined, {tagId});

export const removeTagFromMedia = (mediaId, tagId) => deleteRequest(`/media/${mediaId}/remove_tag`, undefined, {tagId});