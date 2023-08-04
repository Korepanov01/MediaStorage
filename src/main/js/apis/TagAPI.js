import { Api } from "./configs/AxiosConfig"

export const TagAPI = {
    get: function (searchParameters) {
        return  Api.get("/tags", { params: searchParameters });
    }
}
