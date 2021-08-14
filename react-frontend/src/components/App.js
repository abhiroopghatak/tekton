
import React, { useState, lazy, Suspense } from 'react';
import history from "../history";

import { BadRequest } from "./ui/error/badRequest";
import log from "loglevel";
import { Router, Route, Switch } from 'react-router-dom';
import '../styles/App.css';


import HeaderComponent from "./common/HeaderComponent";
import FooterComponent from "./common/FooterComponent";
import QuotaDetails from './routes/namespaces/QuotaDetails.js';
import ClusterAccess from './routes/access/ClusterAccess.js';

const Home = lazy(() => import('./routes/home/home'));
const Sidebar = lazy(() => import('./common/sidebar'));
const renderLoader = () => <p>-------Loading-----------</p>;



const App = () => {
	log.info('[App]: Rendering App Component')

	return (
		<div className="App" id="outer-container">
			<HeaderComponent />
			<div id="page-wrap" >
				<Suspense fallback={renderLoader()}>
					<Sidebar pageWrapId={'page-wrap'} outerContainerId={'outer-container'} /> </Suspense>
				<Router history={history}><Switch>
					<Route path="/home" ><Suspense fallback={renderLoader()}><Home /></Suspense></Route>
					<Route path="/resources" component={QuotaDetails} />
					<Route path="/access" exact component={ClusterAccess} />
					<Route path="*" exact component={BadRequest} />
				</Switch>
				</Router>
			</div>
			<FooterComponent />
		</div>
	)
}


export default App;
