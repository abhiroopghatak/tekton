
import React, { Component } from 'react';
import { MDBContainer } from "mdbreact";
import FixedPriceCard from './FixedPrice.js';
import Namespaces from './Namespaces.js';

class QuotaDetails extends Component {


	render() {
		return (
			<>
				<MDBContainer >
					<div class="row justify-content-center">
						<div class="col-md-4 col-lg-3">
							<FixedPriceCard />
						</div>
						<div class="col-md-5 col-lg-9">

						<Namespaces />
						</div>
					</div>
				</MDBContainer>
			</>
		);
	}
};
export default QuotaDetails;