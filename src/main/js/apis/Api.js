import axios from "axios"
import {authHeader} from "../GetChunks";

export const BASE_URL = "http://localhost:8080/api/";

export const Api = axios.create({
    baseURL: BASE_URL,
    headers: authHeader()
});