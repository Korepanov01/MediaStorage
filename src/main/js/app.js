const React = require('react');
const ReactDOM = require('react-dom');

import 'bootstrap/dist/css/bootstrap.min.css';
import {Header} from "./components/header.js"

function App(props) {
    return (
        <>
            <Header/>
        </>
    );
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)
