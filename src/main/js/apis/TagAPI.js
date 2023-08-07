import { Api } from "./Api"

export const TagAPI = {
    get: function (searchParameters) {
        return  Api.get("/tags", {params: searchParameters});
    },

    getById: function (id) {
        return  Api.get(`/tags/${id}`);
    }
}
