import {postRequest} from "./baseApi";

export const removeAdmin = (userId) => postRequest('/user_role/remove_admin', {userId});

export const giveAdmin = (userId) => postRequest('/user_role/give_admin', {userId});