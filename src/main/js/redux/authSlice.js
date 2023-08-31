import {createSlice} from '@reduxjs/toolkit'

const user = JSON.parse(localStorage.getItem("user"));

const initialState = {user}

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login: (state, action) => {
            state.user = action.payload;
        },

        loginFail: (state) => {
            state.user = null;
        },

        logout: (state) => {
            state.user = null;
        },
    },
});

export const {login, loginFail, logout} = authSlice.actions

export default authSlice.reducer

