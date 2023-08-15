import {Api} from "./Api"
import {TagBuilder} from "../models/Tag";
import {deleteRequest, getRequest, postRequest, putRequest} from "./baseApi";

export const getTagById = (id) => getRequest(`/tags/${id}`);

export const getTags = (params) => getRequest('/tags', params);

export const deleteTag = (tagId) => deleteRequest(`/tags/${tagId}`);

export const putTag = (tagId, payload) => putRequest(`/tags/${tagId}`, payload);

export const postTag = (payload) => postRequest('/tags', payload);
