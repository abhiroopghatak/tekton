
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
						<h2>Price</h2>
						<div class="col-md-4 col-lg-4">
							<FixedPriceCard />
						</div>
						<div class="col-md-5 col-lg-8">
						<Namespaces />
						</div>
					</div>
				</MDBContainer>
			</>
		);
	}
};
export default QuotaDetails;