import {deleteRequest, getRequest, postRequest, putRequest} from "./baseApi";

export const getCategoryById = (id) => getRequest(`/category/${id}`);

export const getCategories = (params) => getRequest('/category', params);

export const deleteCategory = (id) => deleteRequest(`/category/${id}`);

export const putCategory = (id, payload) => putRequest(`/category/${id}`, payload);

export const postCategory = (payload) => postRequest('/category', payload);