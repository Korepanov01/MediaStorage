import {postRequest} from "./baseApi";

export const register = (name, email, password) => postRequest('/auth/register', {name, email, password});

export const login = (email, password) => postRequest('/auth/login', {email, password});