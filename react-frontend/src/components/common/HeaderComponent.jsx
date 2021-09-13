import React, { Component } from 'react'
import { Link } from "react-router-dom";

import { useHistory } from "react-router-dom";
const HeaderComponent = () => {
	const isAuthenticated = JSON.parse(localStorage.getItem('_token'));
	const history = useHistory();
	const logout = () => {
		localStorage.removeItem("_token");
		localStorage.clear();
		console.log('logged out successfully');
		history.push('/login');
	}


	return (
		<div className="page-top sticky-top">
			<nav class="navbar navbar-light navbar-expand-md scrolling-navbar flexible-navbar" role="navigation"><a href="https://www.hcl.com" class="navbar-brand"><strong>HCL</strong></a>
				<button type="button" style={{ textTransform: 'none' }} class="navbar-toggler"><span class="navbar-toggler-icon"></span></button>
				<div class="collapse navbar-collapse">
					{isAuthenticated ? <ul class="navbar-nav mr-auto">

						<li class="nav-item active"><a class="nav-link Ripple-parent" href="/home">Home<div class="Ripple " STYLE="top: 0px; left: 0px; width: 0px; height: 0px;"></div></a></li>

					</ul> : null}
					<ul class="navbar-nav ml-auto">

						<li class="nav-item"><a class="border border-light rounded mr-1 nav-link Ripple-parent" href="/sign-up"><i class="fas fa-user-plus"></i>Register New User</a></li>
						{isAuthenticated ? <button type="button" style={{ textTransform: 'none' }} className="btn btn-dark" onClick={logout}><i class="fas fa-sign-out-alt" ></i>
							Sign out
    </button> : null}
					</ul>
				</div>
			</nav>
		</div>
	);

}

export default HeaderComponent