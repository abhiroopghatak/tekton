
import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import { MDBCard, MDBCardText, MDBCardBody, MDBCardTitle, MDBBadge } from "mdbreact";
import _user_icon from "../../images/icon/_user_icon.png";
import DataService from '../../restapi/data-service/DataService.js';


function UserProfileCard() {
	const isAdmin = JSON.parse(localStorage.getItem('system_role'));
	const [data, setData] = useState([]);

	useEffect(() => {
		var _u_email = JSON.parse(localStorage.getItem('_u_email'));
		loadProfile(_u_email);

	}, []);

	async function loadProfile(_u_email) {
		DataService.getUserByEmail(_u_email).then(

			(response) => {
				setData(response.data);
				console.log(response.data);
			}).catch(error => {
				console.log(error);
			});
	}
	return (

		<MDBCard className="border border-info  z-depth-5" >
			<MDBCardBody> <MDBBadge color="danger" className="ml-3">{data.status === 'A' ?
				'Active' : 'Inactive'}</MDBBadge>
				<img className="img-fluid" src={_user_icon} margin="auto" >

				</img>
				<MDBCardTitle className="h4-responsive">Welcome {data.fullname}</MDBCardTitle>
				<MDBCardText> You have been logged in as {data.email} </MDBCardText>
				{isAdmin === 'AU' ? null :
					<Link to="/access">
						<button type="button" class="btn btn-dark text-light pull-right" >Raise Access Request</button>
					</Link>
				}

				{isAdmin === 'AU' ?
					<Link to="/access-approve">
						<button type="button" class="btn btn-light" >Access Approval</button>
					</Link> : null}
				{isAdmin === 'AU' ?
					<Link to="/user-approve">
						<button type="button" class="btn btn-dark" >User-Approval</button>
					</Link> : null}
			</MDBCardBody>
		</MDBCard>
	);
};
export default UserProfileCard;