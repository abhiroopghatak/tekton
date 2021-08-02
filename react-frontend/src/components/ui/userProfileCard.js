
import React, { useState, useEffect } from 'react';

import { MDBCard, MDBCardText, MDBCardBody, MDBCardTitle, MDBBadge } from "mdbreact";
import _user_icon from "../../images/icon/_user_icon.png";
import DataService from '../../restapi/data-service/DataService.js';


function UserProfileCard() {

	const [data, setData] = useState([]);

	useEffect(async () => {
		DataService.getUserByEmail("abhiroop.g@hcl.com").then(

			(response) => {
				setData(response.data);
				console.log(response.data);
			}).catch(error => {
				console.log(error);
			});

	});

	return (

		<MDBCard className="border border-info  z-depth-5" >
			<MDBCardBody> <MDBBadge color="danger" className="ml-3">{data.status === 'A' ?
				'Active' : 'Inactive'}</MDBBadge>
				<img className="img-fluid" src={_user_icon} width="143" height="80" margin="auto" >

				</img>
						<MDBCardTitle>Welcome {data.fullname}</MDBCardTitle>
				<MDBCardText> You have been logged in as {data.email} </MDBCardText>


				<div class="d-inline p-2 bg-primary text-white">History</div>
				<div class="d-inline p-2 bg-dark text-white">switch</div>
			</MDBCardBody>
		</MDBCard>
	);
};
export default UserProfileCard;