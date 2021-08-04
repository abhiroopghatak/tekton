
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
						<div class="col-md-3 col-lg-3">
							<FixedPriceCard />
						</div>
						<Namespaces />
					</div>
				</MDBContainer>
			</>
		);
	}
};
export default QuotaDetails;