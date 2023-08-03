import { Api } from "./configs/AxiosConfig"

export const MediaAPI = {
    get: function () {
        return  Api.get("/media");
    }
}
