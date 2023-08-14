import {Api, usePost} from "./Api"
import {AuthInfoBuilder} from "../models/AuthInfo";

export const useRegister = (name, email, password) => usePost("/auth/register", {
    name,
    email,
    password,
});

export const useLogin = (email, password) => usePost("/auth/login", {email, password});
