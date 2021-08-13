import React, { useState, useEffect } from "react";
import { MDBContainer, MDBRow, MDBCol, MDBInput, MDBBtn } from 'mdbreact';


import DataService from '../../../restapi/data-service/DataService.js';

const ClusterAccess = () => {
	const [clusters, setClusters] = useState([]);

	useEffect(async () => {
		DataService.getAllClusters().then(
			(response) => {
				setClusters(response.data);
				console.log(response.data);
			}).catch((error) => {
				console.log(error);
			});

	}, []);

	return (



		<div class="container">
			<div class="row">
				<div class="col-md-7 bg-request-access">

				</div>

				<div class="col-md position-relative top-50 start-0">
					<MDBContainer>
						<MDBRow>
							<MDBCol md="12">
								<form>
									<p className="h4 text-center mb-4">Cluster Access Request</p>
									<div className="grey-text">
										<MDBInput containerClass="text-left" icon="envelope" label="Type your email" group type="email" validate error="wrong"
											success="right" />
										<select class="form-select" aria-label="Default select example">
											<option selected>Select the cluster</option>
											{clusters.map((c, index) => <option value="{index}">{c.name} of env {c.environment}</option>)}

										</select>

										<MDBInput containerClass="text-left" label="Key=value lable please ..." icon="pen" group type="email" validate />
									</div>
										<MDBBtn className="position-absolute bottom-20 end-0" color="light-blue">Submit</MDBBtn>
								</form>
							</MDBCol>
						</MDBRow>
					</MDBContainer>

				</div>
			</div>
		</div>

	);
};

export default ClusterAccess;