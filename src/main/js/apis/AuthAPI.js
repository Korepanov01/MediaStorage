import {Api} from "./Api"

export const AuthAPI = {

    register: function (name, email, password) {
        return Api.post("/auth/register", {
            name,
            email,
            password,
        })
    },

    login: function (email, password) {
        return Api.post("/auth/login", {email, password});
    }
}
