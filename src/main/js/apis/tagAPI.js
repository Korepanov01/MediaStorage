import {deleteRequest, getRequest, postRequest, putRequest} from "./baseApi";
import {MAX_PAGE_SIZE} from "../enums/api";

export const getTagById = (id) => getRequest(`/tags/${id}`);

export const getTagByMediaId = (mediaId) => getTags({mediaId, pageSize: MAX_PAGE_SIZE});

export const getTags = (params) => getRequest('/tags', params);

export const deleteTag = (tagId) => deleteRequest(`/tags/${tagId}`);

export const putTag = (tagId, payload) => putRequest(`/tags/${tagId}`, payload);

export const postTag = (payload) => postRequest('/tags', payload);
