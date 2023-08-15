import {request, useRequest} from "./Api";

export const useGetRequest = (url, params) =>  useRequest(url, "GET", null, params);

export const deleteRequest = (url) => request(url, "DELETE");