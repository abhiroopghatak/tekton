

import React, { useState, useEffect } from 'react';
import { MDBCard, MDBCardText, MDBCardBody, MDBCardTitle, MDBBadge } from "mdbreact";
import fixedPrice from "../../../images/fixedPrice.jpg";
import _dollar_cost_icon from "../../../images/icon/_dollar_cost_icon.png";


import DataService from '../../../restapi/data-service/DataService.js';

const FixedPriceCard = (props) => {

	const cid = props.cid;
	const [data, setData] = useState([]);


	const costDataToParent = (data) => {
		props.passCostDataToParent(data);
	}
	useEffect(async () => {
		DataService.getCostDataPerCluster(cid).then(

			(response) => {
				setData(response.data);
				costDataToParent(response.data);
				//console.log(response.data);
			}).catch(error => {
				console.log(error);
			});

	}, []);
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
								<li class="list-group-item h6"  >Compute {data.cpuunit} per {data.timelengthunit}<span class="badge primary-color  pull-right ">{data.cpucost} <i class="fas fa-dollar-sign "></i></span></li>
								<li class="list-group-item h6">Memory {data.memoryunit} per {data.timelengthunit}<span class="badge danger-color badge-danger-color badge-pill pull-right text-dark">{data.momorycost} <i class="fas fa-dollar-sign"></i></span></li>
								<li class="list-group-item h6">Storage {data.storageunit} per {data.timelengthunit}<span class="badge warning-color  badge-pill pull-right text-dark">{data.storagecost} <i class="fas fa-dollar-sign"></i></span></li>
							</ul>
						</div>
					</div>
				</MDBCardBody>
			</MDBCard>
		</>


	);
};
export default FixedPriceCard;