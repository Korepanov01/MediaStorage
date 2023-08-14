import axios from "axios"
import {useEffect, useState} from "react";
import {AuthService} from "../services/AuthService";

export const BASE_URL = "http://localhost:8080/api/";

export const Api = axios.create({
    baseURL: BASE_URL,
    headers: AuthService.getAuthHeader()
});

export const useAxios = (url, method, payload, params) => {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
        (async () => {
            try {
                const response = await Api.request({
                    data: payload,
                    method,
                    url,
                    params
                });
                setData(response.data);
            } catch (error) {
                if (error.response) {
                    setError({
                        status: error.response.status,
                        messages: error.response?.data?.errors ? error.response.data.errors : []
                    });
                } else if (error.request) {
                    setError({
                        status: 0,
                        messages: ["Нет ответа от сервера"]
                    });
                } else {
                    setError({
                        status: 0,
                        messages: ["Неизвестная ошибка"]
                    });
                }
            } finally {
                setLoaded(true);
            }
        })();
    }, []);

    return { data, error, loaded };
};

export const useGet = (url, params) =>  useAxios(url, "GET", null, params);

export const usePost = (url, payload) =>  useAxios(url, "POST", payload);

export const usePut = (url, payload) =>  useAxios(url, "PUT", payload);

export const usePatch = (url, payload) =>  useAxios(url, "PATCH", payload);

export const useDelete = (url, payload) =>  useAxios(url, "DELETE", payload);