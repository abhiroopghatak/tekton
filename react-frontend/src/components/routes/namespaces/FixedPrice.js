

import React from 'react';
import { MDBCard, MDBCardText, MDBCardBody, MDBCardTitle, MDBBadge } from "mdbreact";
import fixedPrice from "../../../images/fixedPrice.jpg";
import _dollar_cost_icon from "../../../images/icon/_dollar_cost_icon.png";
function FixedPriceCard() {

	return (

		<MDBCard className="border border-info  z-depth-5" >
			<MDBCardBody>
				<img className="img-fluid" src={fixedPrice} margin="auto" >

				</img>
				<MDBCardTitle><img className="img-fluid" src={_dollar_cost_icon} margin="auto" >

				</img>Price details </MDBCardTitle>
				<div class="card-body">
					<ul class="list-group list-group-flush">
						<li class="list-group-item">Sales<span class="badge success-color badge-success-color badge-pill pull-right">22%<i class="fa fa-arrow-up ml-1"></i></span></li>
						<li class="list-group-item">Traffic<span class="badge danger-color badge-danger-color badge-pill pull-right">5%<i class="fa fa-arrow-down ml-1"></i></span></li>
						<li class="list-group-item">Orders<span class="badge primary-color badge-primary-color badge-pill pull-right">14</span></li>
						<li class="list-group-item">Issues<span class="badge primary-color badge-primary-color badge-pill pull-right">123</span></li>
						<li class="list-group-item">Messages<span class="badge primary-color badge-primary-color badge-pill pull-right">8</span></li>
					</ul>
				</div>

			</MDBCardBody>
		</MDBCard>

	);
};
export default FixedPriceCard;