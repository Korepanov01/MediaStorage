import {request, useRequest} from "./Api";

export const useGetRequest = (url, params) =>  useRequest(url, "GET", null, params);

export const getRequest = (url, params) => request(url, "GET", undefined, params);

export const deleteRequest = (url) => request(url, "DELETE");

export const putRequest = (url, payload) => request(url, "PUT", payload);