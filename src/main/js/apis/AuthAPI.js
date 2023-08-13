import {Api, usePost} from "./Api"
import {AuthInfoBuilder} from "../models/AuthInfo";

export const AuthAPI = {

    register: function (name, email, password) {
        return Api.post("/auth/register", {
            name,
            email,
            password,
        })
    },

    login: function (email, password) {
        return Api.post("/auth/login", {email, password})
            .then((response) => {
                let authInfo = AuthInfoBuilder.buildByData(response.data);
                localStorage.setItem("user", JSON.stringify(authInfo));
                return authInfo;
            });
    },

    logout: function () {
        localStorage.removeItem("user");
    },
}
