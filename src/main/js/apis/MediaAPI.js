import { Api } from "./Api"
import {MediaBuilder} from "../models/Media";
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

    getById: async function (id) {
        return Api.get(`/media/${id}`)
            .then(response => response.data)
            .then(data => MediaBuilder.buildByData(data));
    },

    post: function (newMedia) {
        return  Api.post("/media", newMedia);
    },

    put: function (id, media) {
        return  Api.put(`/media/${id}`, media);
    },

    delete: function (id) {
        return  Api.delete(`/media/${id}`);
    }
}
