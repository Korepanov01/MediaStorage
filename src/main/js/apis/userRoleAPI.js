import {postRequest} from "./baseApi";

export const removeAdmin = (userId) => postRequest(`/users/${userId}/remove_admin`);

export const giveAdmin = (userId) => postRequest(`/users/${userId}/give_admin`);