import {request} from "./Api";

export const getRequest = (url, params) => request(url, "GET", undefined, params);

export const deleteRequest = (url) => request(url, "DELETE");

export const putRequest = (url, payload) => request(url, "PUT", payload);

export const postRequest = (url, payload) => request(url, "POST", payload);