import React from 'react';
import ReactDOM from 'react-dom';
import {HashRouter} from 'react-router-dom';

import './styles/common.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbreact/dist/css/mdb.css';
import * as serviceWorker from "./serviceWorker";


import App from './components/App';

import 'bootstrap/dist/css/bootstrap.min.css';


ReactDOM.render(
	<HashRouter>
		<App />
	</HashRouter>,
	document.getElementById('root')
);


serviceWorker.unregister();