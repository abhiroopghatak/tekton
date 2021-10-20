
import React, { useState, useEffect } from 'react';
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
		memoryunit: "Gi",
		momorycost: 0,
		storagecost: 0,
		storageunit: "Gi",
		timelengthunit: "month",


	});

	const location = useLocation();
	useEffect(() => {
		window.onbeforeunload = function() {
			localStorage.setItem("cluster", JSON.stringify(cluster));
			return true;
		};

		return () => {
			window.onbeforeunload = null;
		};
	}, []);
	const [cluster, setCluster] = useState(location.state ? location.state.cluster : JSON.parse(localStorage.getItem('cluster')));
	const [costData, SetCostData] = useState(initialCostData);

	return (
		<>
			<MDBContainer >
				<div class="row justify-content-center">
					<div class="col-md-4 col-lg-3">
						<FixedPriceCard cid={cluster.uuid} passCostDataToParent={SetCostData} />
					</div>
					<div class="col-md-5 col-lg-9">

						<Namespaces cluster={cluster} costData={costData} />
					</div>
				</div>
			</MDBContainer>
		</>
	);

};
export default QuotaDetails;