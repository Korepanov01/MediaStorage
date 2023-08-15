import {getRequest} from "./baseApi";

export const getMediaTypes = (params) => getRequest('/media_type', params);

export const getMediaTypeById = (id) => getRequest(`/media_type/${id}`);