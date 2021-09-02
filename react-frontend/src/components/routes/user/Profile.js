import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";
import DataService from '../../../restapi/data-service/DataService.js';




const Profile = () => {

	const [user, setUser] = useState();
	useEffect(async () => { loadData(); }, []);


	const loadData = () => {
		var _u_email = JSON.parse(localStorage.getItem('_u_email'));
		DataService.getUserByEmail(_u_email).then(

			(response) => {
				setUser(response.data);
				console.log(response.data);
			}).catch(error => {
				console.log(error);
			});

	}

	return (<>


		<div className="outer">
			<div className="inner">
				<h4> Profile Details</h4>
				<div class="card text-center">
				
				{(user)? <ul class="list-group list-group-flush">
						<li class="list-group-item">
							<div class="list-group-item-fixed">
								<strong className="list-group-left">Full Name : </strong>
								<span className="list-group-right">{user.fullname}</span>
							</div>
						</li>
						<li class="list-group-item">
							<div class="list-group-item-fixed">
								<strong class="list-group-left">Email : </strong>
								<span class="list-group-right">{user.email}</span>
							</div>
						</li>
						<li class="list-group-item">
							<div class="list-group-item-fixed">
								<strong class="list-group-left">Last Updated At</strong>
								<span class="list-group-right">{user.createTime}</span>
							</div>
						</li>
						<li class="list-group-item">
							<div class="list-group-item-fixed">
								<strong class="list-group-left">Status </strong>
								<span class="list-group-right">{user.status}</span>
							</div>
						</li>
						<li class="list-group-item">
							<div class="list-group-item-fixed">
								<strong class="list-group-left">Role : </strong>
								<span class="list-group-right">{(user.role === 'AU') ? "Admin" : "End User"}</span>
							</div>
						</li>

					</ul>: "User Data not loaded"}
					
				</div>
				<Link to="/change/password">
				<button type="button" style={{ textTransform: 'none' }} className="btn btn-dark btn-lg btn-block">Change Password</button>
				</Link>
			</div>
		</div>


	</>);
}


export default Profile