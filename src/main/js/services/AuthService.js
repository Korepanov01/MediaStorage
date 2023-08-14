import {AuthAPI} from "../apis/AuthAPI";

export const AuthService = {
    login: (email, password) => {
        return AuthAPI.login(email, password)
            .then(response => {
                localStorage.setItem("user", JSON.stringify(response.data));
                return response.data;
            });
    },

    logout: () => {
        localStorage.removeItem("user");
    },

    register: (name, email, password) => {
        return AuthAPI.register(name, email, password);
    },

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    }
}