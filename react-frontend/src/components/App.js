
import React, { lazy, Suspense } from 'react';
import history from "../history";

import { BadRequest } from "./ui/error/badRequest";
import log from "loglevel";
import { Router, Route, Switch } from 'react-router-dom';

import '../styles/App.css';

import SpinnerPage from "./ui/spinner";
import HeaderComponent from "./common/HeaderComponent";
import FooterComponent from "./common/FooterComponent";
import QuotaDetails from './routes/namespaces/QuotaDetails.js';
import ClusterAccess from './routes/access/ClusterAccess.js';
import Login from "./login/login.function.js";
import SignUp from "./login/signup.function.js";
const Home = lazy(() => import('./routes/home/home'));
const Sidebar = lazy(() => import('./common/sidebar'));
const renderLoader = () => <SpinnerPage/>;



const App = () => {
	log.info('[App]: Rendering App Component')

	return (
		<div className="App" id="outer-container">
			<HeaderComponent />
			<div id="page-wrap" >
				<Suspense fallback={renderLoader()}>
					<Sidebar pageWrapId={'page-wrap'} outerContainerId={'outer-container'} /> </Suspense>
				<Router history={history}><Switch>
					<Route path="/sign-in" exact component={Login} />
					<Route path="/sign-up" exact component={SignUp} />
					<Route path="/" exact ><Suspense fallback={renderLoader()}><Home /></Suspense></Route>
					<Route path="/resources" component={QuotaDetails} />
					<Route path="/access" exact component={ClusterAccess} />
					<Route path="*" component={BadRequest} />
				</Switch>
				</Router>
			</div>
			<FooterComponent />
		</div>
	)
}


export default App;