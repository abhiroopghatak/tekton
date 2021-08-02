
import React from 'react';

import { MDBCard,MDBCardText, MDBCardBody, MDBCardTitle } from "mdbreact";
import _user_icon from "../../images/icon/_user_icon.png";

function UserProfileCard() {
return (
<MDBCard className="border border-info">
					<MDBCardBody> <img className="img-fluid" src={_user_icon} width="193" height="130" margin="auto" />
						<MDBCardTitle>Welcome User</MDBCardTitle>
						<MDBCardText> You have been logged in as user@email.com and below are relevant details. user name
							<br /> user status </MDBCardText>
					</MDBCardBody>
				</MDBCard>
				);
};
export default UserProfileCard;