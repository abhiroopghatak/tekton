import React from "react";
import { useHistory } from "react-router-dom";
import { slide as Menu } from "react-burger-menu";
import '../../styles/componentstyles/Sidebar.css';

export default props => {

	const isAdmin = JSON.parse(localStorage.getItem('system_role'));
	const isAuthenticated = JSON.parse(localStorage.getItem('_token'));
	const history = useHistory();
	const logout = () => {
		localStorage.removeItem("_token");
		localStorage.clear();
		console.log('logged out successfully');
		history.push('/login');
	}
	
	return (
		<Menu {...props}>
			<a className="menu-item" href="/home">
				Home
      </a>

			<a className="menu-item" href="/profile">
				Profile
      </a>

			<a className="menu-item" href="/access">
				Cluster Access request
      </a>

			<a className="menu-item" href="/contact">
				Contact us
      </a>
			{isAdmin === 'AU' ?

				<a className="menu-item" href="/user-approve">
					Manage Users
      </a> : null
			}
			{isAdmin === 'AU' ?

				<a className="menu-item" href="/manageusers">
					View All Users
      </a> : null
			}

			{isAdmin === 'AU' ?
				<a className="menu-item" href="/access-approve">
					Cluster Access Approval
      </a> : null}
		
		{isAuthenticated ? <a className="menu-item" href="" onClick={logout}>
					Sign Out
      </a> : null}
		</Menu>
	);
};