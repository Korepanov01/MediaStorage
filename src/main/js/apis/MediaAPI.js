import { Api } from "./Api"

export const MediaAPI = {
    get: function (searchParameters) {
        return  Api.get("/media", { params: searchParameters });
    }
}
