import {getRequest} from "./baseApi";

export const getMediaFiles = (params) => getRequest('/media_file', params);