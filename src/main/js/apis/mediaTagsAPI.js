import {deleteRequest, postRequest} from "./baseApi";

export const postMediaTag = (mediaId, tagId) => postRequest('/media_tag', {mediaId, tagId});

export const deleteMediaTag = (mediaId, tagId) => deleteRequest('/media_tag', {mediaId, tagId});