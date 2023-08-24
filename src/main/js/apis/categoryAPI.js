import {deleteRequest, getRequest, postRequest, putRequest} from "./baseApi";

export const getCategoryById = (id) => getRequest(`/category/${id}`);

export const getChildrenCategory = (parentCategoryId) => getRequest(`/category/${parentCategoryId}/children`)

export const deleteCategory = (id) => deleteRequest(`/category/${id}`);

export const putCategory = (id, name, parentCategoryId) => putRequest(`/category/${id}`, {name, parentCategoryId});

export const postCategory = (name, parentCategoryId = null) => postRequest('/category', {name, parentCategoryId});