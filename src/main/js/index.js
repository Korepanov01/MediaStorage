import {createRoot} from "react-dom/client";

const React = require('react');

import 'bootstrap/dist/css/bootstrap.min.css';

import { App } from "./components/App"

createRoot(document.getElementById('react')).render(<App/>);
