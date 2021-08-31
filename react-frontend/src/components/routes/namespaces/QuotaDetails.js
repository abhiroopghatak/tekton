
import React from 'react';
import { useLocation } from "react-router-dom";
import { MDBContainer } from "mdbreact";
import FixedPriceCard from './FixedPrice.js';
import Namespaces from './Namespaces.js';

const QuotaDetails = () => {
	const location = useLocation()
	const   cid = location.state.cid;


	return (
		<>
			<MDBContainer >
				<div class="row justify-content-center">
					<div class="col-md-4 col-lg-3">
						<FixedPriceCard cid={cid} />
					</div>
					<div class="col-md-5 col-lg-9">

							<Namespaces  cid={cid}/>
					</div>
				</div>
			</MDBContainer>
		</>
	);

};
export default QuotaDetails;