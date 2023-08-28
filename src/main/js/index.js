import {createRoot} from "react-dom/client";

const React = require('react');

import 'bootstrap/dist/css/bootstrap.min.css';
import "react-toastify/dist/ReactToastify.css";
import {App} from "./components/app"
import {HashRouter as Browser} from 'react-router-dom';
import {store} from './redux/store'
import {Provider} from 'react-redux'
import {ToastContainer} from "react-toastify";

createRoot(document.getElementById('react')).render(
    <Provider store={store}>
        <Browser>
            <ToastContainer
                position="bottom-right"
                autoClose={2000}
                closeOnClick
            />
            <App/>
        </Browser>
    </Provider>
);
