import {deleteRequest, getRequest} from "./baseApi";

export const getUsers = (params) => getRequest('/users', params);

export const deleteUser = (id) => deleteRequest(`/users/${id}`);