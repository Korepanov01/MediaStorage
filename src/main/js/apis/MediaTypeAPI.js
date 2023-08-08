import { Api } from "./Api"

export const MediaTypeAPI = {
    get: function () {
        return  Api.get("/media_type");
    }
}
