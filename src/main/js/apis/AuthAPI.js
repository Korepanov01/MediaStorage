import {Api, usePost} from "./Api"

export const useRegister = (name, email, password) => usePost("/auth/register", {
    name,
    email,
    password,
});

export const useLogin = (email, password) => usePost("/auth/login", {email, password});

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
