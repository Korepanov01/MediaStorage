import {Api, BASE_URL, convertError} from "./api"
import {deleteRequest, getRequest} from "./baseApi";
import {toastErrors} from "../services/toastService";
import {AuthService} from "../services/authService";

export const getFileUrl = (id) => BASE_URL + `files/${id}`;

export const getFileById = (id) => getRequest(`/files/${id}`);

export const deleteFile = (id) => deleteRequest(`/files/${id}`);

export const postFile = (file, fileTypeId, mediaId) => {
    let result = {
        data: null,
        error: null
    }

    const formData = new FormData();
    formData.append('file', file);

    return  Api.postForm('/files', formData, {
        headers: {
            ...AuthService.getAuthHeader(),
            'Content-Type': 'multipart/form-data'
        },
        params: {fileTypeId, mediaId}
    })
        .then(response => result.data = response.data)
        .catch(error => {
            result.error = convertError(error);
            toastErrors(result.error.messages);
        })
        .then(() => result);
}