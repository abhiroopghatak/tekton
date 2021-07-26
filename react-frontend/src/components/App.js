
import React, { useState } from 'react';
import history from "../history";
import { BadRequest } from "./ui/error/badRequest";
import log from "loglevel";
import { Router, Route, Switch } from 'react-router-dom';
import '../styles/App.css';


import HeaderComponent from "./common/HeaderComponent";
import FooterComponent from "./common/FooterComponent";


import Home from "./routes/home/home";
import Sidebar from './common/sidebar';

const App = () => {
	log.info('[App]: Rendering App Component')
	const [serverError, setServerError] = useState(false);

	const setServerErrorHandler = () => {
		setServerError(true)
	}

	return (
		<div className="App" id="outer-container">
			<HeaderComponent />
			<div id="page-wrap" >
				<Sidebar pageWrapId={'page-wrap'} outerContainerId={'outer-container'} />
				<Router history={history}>
					{serverError ? null : <Switch>
						<Route path="/home" exact component={Home} />

						<Route path="*" exact component={BadRequest} />
					</Switch>}

					<FooterComponent />
				</Router>
			</div>
		</div>
	)
}


export default App;
