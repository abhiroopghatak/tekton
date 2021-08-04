

import React from 'react';
import { MDBCard, MDBCardText, MDBCardBody, MDBCardTitle, MDBBadge } from "mdbreact";
import fixedPrice from "../../../images/fixedPrice.jpg";
import _dollar_cost_icon from "../../../images/icon/_dollar_cost_icon.png";
function FixedPriceCard() {

	return (
		<>
			<MDBCard className="border border-info  z-depth-5" >
				<MDBCardTitle><img className="img-fluid" src={_dollar_cost_icon} margin="auto" >

				</img>Price details </MDBCardTitle>
				<MDBCardBody>
					<img className="img-fluid" src={fixedPrice} margin="auto" >

					</img>


					<div className="card mb-4">
						<div className="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item h5"  >vCPU per Month<span class="badge primary-color  pull-right">220 <i class="fas fa-dollar-sign"></i></span></li>
								<li class="list-group-item h5">Memory 1G per month<span class="badge danger-color badge-danger-color badge-pill pull-right">10 <i class="fas fa-dollar-sign"></i></span></li>

							</ul>
						</div>
					</div>
				</MDBCardBody>
			</MDBCard>
		</>


	);
};
export default FixedPriceCard;