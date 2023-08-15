import axios from "axios";
import {AuthService} from "../services/authService";
import {toastErrors} from "../services/toastService";

export const BASE_URL = "http://localhost:8080/api/";

export const Api = axios.create({
    baseURL: BASE_URL,
});

export const request = (url, method, payload, params) => {
    let result = {
        data: null,
        error: null
    };

    return (Api
        .request({data: payload, method, url, params, headers: AuthService.getAuthHeader(), responseType: "json"})
        .then(response => result.data = response.data)
        .catch(error => {
            result.error = convertError(error);
            toastErrors(result.error.messages);
        }))
        .then(() => result);
};

function convertError(error) {
    let errorObj = {
        status: 0,
        messages: []
    };
    if (error.response) {
        errorObj.status = error.response.status;
        errorObj.messages = error.response?.data?.errors ? error.response.data.errors : [];

        if (errorObj.status === 401)
            errorObj.messages.push("Неавторизован");
        if (errorObj.status === 403)
            errorObj.messages.push("Нет доступа к ресурсу");

        if (errorObj.messages.length === 0)
            errorObj.messages.push("Ошибка!");
    } else {
        errorObj.status = 0;
        errorObj.messages = error.request ? ["Нет ответа от сервера"] : ["Неизвестная ошибка"];
    }

    return errorObj;
}