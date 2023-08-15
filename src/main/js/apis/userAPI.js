import { Api } from "./api"

export const UserAPI = {
    getById: function (id) {
        return Api.get(`/users/${id}`);
    }
}
