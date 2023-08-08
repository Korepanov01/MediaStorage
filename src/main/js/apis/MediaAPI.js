import { Api } from "./Api"
const qs = require('qs');

export const MediaAPI = {
    get: function (searchParameters) {
        return  Api.get('/media', {
            params: searchParameters,
            paramsSerializer: params => {
                console.log(JSON.stringify(params))
                return qs.stringify(params);
            }
        });
    },

    getById: function (id) {
        return  Api.get(`/media/${id}`);
    },

    post: function (newMedia) {
        return  Api.post("/media", newMedia);
    },

    put: function (id, media) {
        return  Api.put(`/media/${id}`, media);
    }
}
