import {getRequest} from "./baseApi";

export const getUsers = (params) => getRequest('/users', params);

export const deleteUser = (id) => getRequest(`/users/${id}`);