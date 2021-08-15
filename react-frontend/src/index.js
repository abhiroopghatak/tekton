import React from 'react';
import ReactDOM from 'react-dom';
import './styles/common.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbreact/dist/css/mdb.css';



import App from './components/App';
import reportWebVitals from './reportWebVitals';

import log from 'loglevel';
import 'bootstrap/dist/css/bootstrap.min.css';

window.React1 = require('react');

// Add this in your component file
require('react-dom');
window.React2 = require('react');
console.log(window.React1 === window.React2);

ReactDOM.render(
	<React.StrictMode>
		<App />
	</React.StrictMode>,
	document.getElementById('root')
);


reportWebVitals();