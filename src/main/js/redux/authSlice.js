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
            state.user = action.payload.user;
        },

        loginFail: (state) => {
            state.isLoggedIn = false;
            state.user = null;
        },

        loginOut: (state) => {
            state.isLoggedIn = false;
            state.user = null;
        },
    },
});

export const { login, loginFail, loginOut } = authSlice.actions

export default authSlice.reducer

