import React from "react";
import { slide as Menu } from "react-burger-menu";
import '../../styles/componentstyles/Sidebar.css';


export default props => {

	const isAdmin = JSON.parse(localStorage.getItem('system_role'));
	return (
		<Menu {...props}>
			<a className="menu-item" href="/">
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

		</Menu>
	);
};