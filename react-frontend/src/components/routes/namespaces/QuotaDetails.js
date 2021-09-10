
import React, { useState } from 'react';
import { useLocation } from "react-router-dom";
import { MDBContainer } from "mdbreact";
import FixedPriceCard from './FixedPrice.js';
import Namespaces from './Namespaces.js';

const QuotaDetails = () => {
	const initialCostData = Object.freeze({
		cpucost: "",
		cpuunit: "",
		currency: "$",
		lastupdated: "",
		memoryunit: "1Gi",
		momorycost: 0,
		storagecost: 0,
		storageunit: "1Gi",
		timelengthunit: "month",


	});


	const location = useLocation()
	const cluster = location.state.cluster;
	const [costData, SetCostData] = useState(initialCostData);

	return (
		<>
			<MDBContainer >
				<div class="row justify-content-center">
					<div class="col-md-4 col-lg-3">
						<FixedPriceCard cid={cluster.uuid} passCostDataToParent={SetCostData} />
					</div>
					<div class="col-md-5 col-lg-9">

						<Namespaces cluster={cluster}  costData={costData} />
					</div>
				</div>
			</MDBContainer>
		</>
	);

};
export default QuotaDetails;