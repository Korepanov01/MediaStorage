import axios from "axios"
import {useEffect, useState} from "react";
import {AuthService} from "../services/AuthService";

export const BASE_URL = "http://localhost:8080/api/";

export const Api = axios.create({
    baseURL: BASE_URL,
    headers: AuthService.getAuthHeader()
});

export const useRequest = (url, method, payload, params) => {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
        (async () => {
            try {
                let response = await Api.request({
                    data: payload,
                    method,
                    url,
                    params
                });
                setData(response.data);
            } catch (error) {
                setError(convertError(error));
            } finally {
                setLoaded(true);
            }
        })();
    }, [params, url]);

    return {data, error, loaded};
};

export const request = (url, method, payload, params) => {
    let result = {
        data: null,
        error: null
    };

    return (Api
        .request({data: payload, method, url, params })
        .then(response => result.data = response.data)
        .catch(error => result.error = convertError(error)))
        .then(() => result);
};

function convertError(error) {
    let errorObj = {
        status: null,
        messages: null
    };

    if (error.response) {
        errorObj.status = error.response.status;
        errorObj.messages = error.response?.data?.errors ? error.response.data.errors : [];
    } else {
        errorObj.status = 0;
        errorObj.messages = error.request ? ["Нет ответа от сервера"] : ["Неизвестная ошибка"];
    }

    return errorObj;
}