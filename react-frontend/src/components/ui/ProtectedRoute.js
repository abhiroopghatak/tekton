import React from 'react'
import { Redirect } from 'react-router-dom'

class ProtectedRoute extends React.Component {

	render() {
		const Component = this.props.component;
		const forAdmin = this.props.forAdmin;
		const isAuthenticated = JSON.parse(localStorage.getItem('_token'));
		const isAdmin = JSON.parse(localStorage.getItem('system_role'));

		return isAuthenticated ? (

			forAdmin === 'true' ? (isAdmin === 'AU' ? <Component /> : <Redirect to={{ pathname: '/home' }} />) :
				<Component />
		) : (
				<Redirect to={{ pathname: '/login' }} />
			);
	}
}

export default ProtectedRoute;