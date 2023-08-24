import {Api, BASE_URL, convertError} from "./api"
import {deleteRequest, getRequest} from "./baseApi";
import {toastErrors} from "../services/toastService";
import {AuthService} from "../services/authService";

export const getFileUrl = (id) => BASE_URL + `files/${id}`;

export const getFileById = (id) => getRequest(`/files/${id}`);

export const deleteFile = (mediaId, fileId) => deleteRequest(`/media/${mediaId}/delete_file`, null, {fileId});

export const postFile = (file, fileTypeId, mediaId) => {
    let result = {
        data: null,
        error: null
    }

    const formData = new FormData();
    formData.append('file', file);

    return  Api.postForm(`/media/${mediaId}/add_file`, formData, {
        headers: {
            ...AuthService.getAuthHeader(),
            'Content-Type': 'multipart/form-data'
        },
        params: {fileTypeId}
    })
        .then(response => result.data = response.data)
        .catch(error => {
            result.error = convertError(error);
            toastErrors(result.error.messages);
        })
        .then(() => result);
}