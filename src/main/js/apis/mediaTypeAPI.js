import { Api } from "./api"

export const MediaTypeAPI = {
    get: function () {
        return  Api.get("/media_type");
    }
}
