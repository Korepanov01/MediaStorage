import { Api } from "./Api"

export const CategoryAPI = {
    get: function (searchParameters) {
        return  Api.get("/category", {params: searchParameters});
    },
}
