import { Api } from "./Api"

export const UserAPI = {
    getById: function (id) {
        return Api.get(`/users/${id}`);
    }
}
