import {request} from "./api";

export const getRequest = (url, params) => request(url, "GET", undefined, params);

export const deleteRequest = (url, payload) => request(url, "DELETE", payload);

export const putRequest = (url, payload) => request(url, "PUT", payload);

export const postRequest = (url, payload) => request(url, "POST", payload);