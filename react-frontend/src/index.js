import React from 'react';
import ReactDOM from 'react-dom';
import './styles/common.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbreact/dist/css/mdb.css';



import App from './components/App';
import reportWebVitals from './reportWebVitals';
import { Provider } from "react-redux";
import { createStore, applyMiddleware, compose } from "redux";

import log from 'loglevel';
import 'bootstrap/dist/css/bootstrap.min.css';


let composeEnhancers

// enable logs & redux only in production.
if (process.env.REACT_APP_ENVIRONMENT === "dev") {

	// by default set the level to info
	log.setLevel("info")
	composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose();
} else {
	console.log = console.error = console.warn = function() { }
	log.disableAll(true)
	composeEnhancers = compose();
}

ReactDOM.render(
	<React.StrictMode>
		<App />
	</React.StrictMode>,
	document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
