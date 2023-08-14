import { createSlice } from '@reduxjs/toolkit'

const user = JSON.parse(localStorage.getItem("user"));

const initialState = user
    ? { isLoggedIn: true, user }
    : { isLoggedIn: false, user: null };

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login: (state, action) => {
            state.isLoggedIn = true;
            state.user = action.payload;
        },

        loginFail: (state) => {
            state.isLoggedIn = false;
            state.user = null;
        },

        logout: (state) => {
            state.isLoggedIn = false;
            state.user = null;
        },
    },
});

export const { login, loginFail, logout } = authSlice.actions

export default authSlice.reducer

