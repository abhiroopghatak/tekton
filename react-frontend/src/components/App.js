
import React, { lazy, Suspense } from 'react';
import history from "../history";

import { BadRequest } from "./ui/error/badRequest";
import log from "loglevel";
import { Router, Route, Switch } from 'react-router-dom';

import '../styles/App.css';

import ProtectedRoute from './ui/ProtectedRoute';
import SpinnerPage from "./ui/spinner";
import HeaderComponent from "./common/HeaderComponent";
import FooterComponent from "./common/FooterComponent";
import AddCluster from './routes/admin/addcluster.function.js';
import AccessApproval from './routes/admin/AccessApproval.function.js';

import QuotaDetails from './routes/namespaces/QuotaDetails.js';
import ClusterAccess from './routes/access/ClusterAccess.js';
import Login from "./login/login.function.js";
import SignUp from "./login/signup.function.js";
import  Home from './routes/home/home';
const Sidebar = lazy(() => import('./common/sidebar'));
const renderLoader = () => <SpinnerPage />;



const App = () => {
	log.info('[App]: Rendering App Component')

	return (
		<div className="App" id="outer-container">
			<Router history={history}>
				<HeaderComponent />
				<div id="page-wrap" >
					<Suspense fallback={renderLoader()}>
						<Sidebar pageWrapId={'page-wrap'} outerContainerId={'outer-container'} /> </Suspense>
					<Switch>

						<ProtectedRoute path="/home" component={Home} / >
						<Route path={["/", "/login"]} exact component={Login} />
						<Route path="/sign-up" component={SignUp} />
						<ProtectedRoute path="/resources" component={QuotaDetails} />
						<ProtectedRoute path="/access" exact component={ClusterAccess} />
						<ProtectedRoute path="/add-cluster" exact component={AddCluster} forAdmin="true" />
						<ProtectedRoute path="/access-approve" exact component={AccessApproval} forAdmin="true" />
						<Route path="*" component={BadRequest} />
					</Switch>

				</div>
				<FooterComponent />
			</Router>
		</div>
	)
}


export default App;