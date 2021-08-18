import React, { Component } from 'react'
import { Link } from "react-router-dom";

class HeaderComponent extends Component {
	constructor(props) {
		super(props)

		this.state = {

		}
	}

logout() {
		localStorage.removeItem("token");
		 window.location.href("/sign-in");
	}

	render() {
		return (
			<div className="page-top sticky-top">
				<nav class="navbar navbar-light navbar-expand-md scrolling-navbar flexible-navbar" role="navigation"><a href="/" class="navbar-brand"><strong>HCL</strong></a>
					<button type="button" class="navbar-toggler"><span class="navbar-toggler-icon"></span></button>
					<div class="collapse navbar-collapse">
						<ul class="navbar-nav mr-auto">

							<li class="nav-item active"><a class="nav-link Ripple-parent" href="/home">Home<div class="Ripple " STYLE="top: 0px; left: 0px; width: 0px; height: 0px;"></div></a></li>
							<li class="nav-item"><a rel="noopener noreferrer" class="nav-link Ripple-parent" href="https://mdbootstrap.com/react/5-min-quick-start/" target="_blank">About KubeTime</a></li>
						</ul>
						<ul class="navbar-nav ml-auto">
							<li class="nav-item"><a class="nav-link navbar-link" href="/login"><i class="fa fa-facebook"></i>login</a></li>
						<li class="nav-item"><a class="border border-light rounded mr-1 nav-link Ripple-parent" href="/sign-up"><i class="fas fa-user-plus"></i>Register</a></li>
							<li class="nav-item"><a class="border border-light rounded mr-1 nav-link Ripple-parent" rel="noopener noreferrer" href="/profile" ><i class="fa fa-id-card" aria-hidden="true"></i> Profile</a></li>
							<li class="nav-item"><a class="border border-light rounded mr-1 nav-link Ripple-parent" rel="noopener noreferrer" onclick="logout()" ><i class="fas fa-sign-out-alt" ></i> Sign Out</a></li>
						</ul>
					</div>
				</nav>
			</div>
		)
	}
}

export default HeaderComponent