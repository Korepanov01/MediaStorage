import {Api} from "./api"

export const CategoryAPI = {
    get: function (searchParameters) {
        return  Api.get("/category", {params: searchParameters});
    },
}