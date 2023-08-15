import {getRequest} from "./baseApi";

export const getMediaFiles = (params) => getRequest('/media_file', params);

export const getMediaFilesByMediaId = (mediaId) => getRequest('/media_file', {mediaId, pageSize: Number.MAX_VALUE});