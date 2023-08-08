import {createRoot} from "react-dom/client";

const React = require('react');

import 'bootstrap/dist/css/bootstrap.min.css';

import { App } from "./components/App"

export const USER_ID = 1;

createRoot(document.getElementById('react')).render(<App/>);
