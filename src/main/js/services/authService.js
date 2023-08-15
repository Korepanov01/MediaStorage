import {login, register} from "../apis/authAPI";

export const AuthService = {
    login: (email, password) => {
        return login(email, password)
            .then(({error, data}) => {
                if (!error) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                    return response.data;
                }
            });
    },

    logout: () => {
        localStorage.removeItem("user");
    },

    register: (name, email, password) => {
        return register(name, email, password);
    },

    getCurrentUser: () => {
        return JSON.parse(localStorage.getItem('user'));
    },

    getAuthHeader: () => {
        const user = JSON.parse(localStorage.getItem('user'));
        if (user && user.jwt) {
            return {Authorization: 'Bearer ' + user.jwt};
        } else {
            return {};
        }
    }
}