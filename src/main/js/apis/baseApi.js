import {request} from "./api";

export const getRequest = (url, params) => request(url, "GET", undefined, params);

export const deleteRequest = (url, payload = undefined, params = undefined) => request(url, "DELETE", payload, params);

export const putRequest = (url, payload) => request(url, "PUT", payload);

export const postRequest = (url, payload = undefined, params = undefined) => request(url, "POST", payload, params);