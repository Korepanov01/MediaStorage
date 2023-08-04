import { Api } from "./Api"

export const FileAPI = {
    get: function (id) {
        return  Api.get(`/files/${id}`);
    }
}
