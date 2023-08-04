import { Api } from "./configs/AxiosConfig"

export const MediaAPI = {
    get: function (searchParameters) {
        return  Api.get("/media", { params: searchParameters });
    }
}
