import axios from "axios"
import {authHeader} from "../GetChunks";
import {useEffect, useRef, useState} from "react";

export const BASE_URL = "http://localhost:8080/api/";

export const Api = axios.create({
    baseURL: BASE_URL,
    headers: authHeader()
});

export const useAxios = (url, method, payload) => {
    const [data, setData] = useState(null);
    const [error, setError] = useState("");
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
        (async () => {
            try {
                const response = await axios.request({
                    data: payload,
                    method,
                    url,
                });
                setData(response.data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoaded(true);
            }
        })();
    }, []);

    return { data, error, loaded };
};

const useGet = (url, payload) =>  useAxios(url, "GET", payload);

const usePost = (url, payload) =>  useAxios(url, "POST", payload);

const usePut = (url, payload) =>  useAxios(url, "PUT", payload);

const usePatch = (url, payload) =>  useAxios(url, "PATCH", payload);