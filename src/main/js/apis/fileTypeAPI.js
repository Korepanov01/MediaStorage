import {getRequest} from "./baseApi";

export const getFileTypes = () => getRequest('/file_types');

export const getFileTypeById = (id) => getRequest(`/file_types/${id}`);