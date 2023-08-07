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
    }
}
