import {login, register} from "../apis/authAPI";
import {store} from "../redux/store";
import {logout as actionLogout, login as actionLogin} from "../redux/authSlice";

export const AuthService = {
    login: (email, password) => {
        return login(email, password)
            .then(({error, data: user}) => {
                if (!error) {
                    localStorage.setItem("user", JSON.stringify(user));
                    store.dispatch(actionLogin(user));
                }
                return {error, user};
            });
    },

    logout: () => {
        localStorage.removeItem("user");
        store.dispatch(actionLogout());
    },

    register: (name, email, password) => {
        return register(name, email, password);
    },

    getCurrentUser: () => {
        return JSON.parse(localStorage.getItem('user'));
    },

    getAuthHeader: () => {
        let userItem = localStorage.getItem('user');
        if (!userItem) return {};
        return {Authorization: 'Bearer ' + (JSON.parse(userItem)).jwt};
    }
}