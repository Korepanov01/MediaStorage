import {getRequest} from "./baseApi";
import {MAX_PAGE_SIZE} from "../enums/api";

export const getMediaFiles = (params) => getRequest('/media_file', params);

export const getMediaFilesByMediaId = (mediaId) => getRequest('/media_file', {mediaId, pageSize: MAX_PAGE_SIZE});