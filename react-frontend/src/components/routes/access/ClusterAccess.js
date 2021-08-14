import React, { useState, useEffect } from "react";

import { MDBContainer, MDBRow, MDBCol, MDBInput, MDBBtn } from 'mdbreact';
import { Link } from "react-router-dom";
import DataService from '../../../restapi/data-service/DataService.js';

const ClusterAccess = () => {

	const initialFormData = Object.freeze({
		jsUserEmail: "",
		clusterUniqueId: "",
		accessedLabel: ""
	});

	const [clusters, setClusters] = useState([]);

	const [formData, updateFormData] = useState(initialFormData);
	const [result, setResult] = useState([]);

	useEffect(async () => {
		DataService.getAllClusters().then(
			(response) => {
				setClusters(response.data);
				console.log(response.data);
			}).catch((error) => {
				console.log(error);
			});

	}, []);


	const handleChange = (e) => {

		updateFormData({
			...formData,
			[e.target.name]: e.target.value.trim()
		});
	};


	const handleSubmit = (e) => {
		e.preventDefault()
		console.log(formData);

		DataService.raiseClusterAccess(formData).then(
			(response) => {
				console.log(response.data);
				setResult(response.data.status);
			}).catch((error) => {
				setResult('F');
				console.log(error);
			});
	};

	return (

		<div class="container">
			<div class="row">
				<div class="col-md-7 bg-request-access">

				</div>

				<div class="col-md position-relative top-50 start-0">
					<MDBContainer>
						<MDBRow>
							<MDBCol md="12">
								{(result === 'S') ?
									<div id="success-alert" class="alert alert-success" role="alert" >
										Resquest has been raised successfully
								</div> : ''}

								{(result === 'F') ? <div id="success-alert" class="alert alert-warning" role="alert" >
									Request couldnt be raised due to data error . Please connect Admin!!!
								</div> : ''}
								<form onSubmit={handleSubmit}>
									<p className="h4 text-center mb-4">Cluster Access Request</p>
									<div className="grey-text">
										<MDBInput containerClass="text-left" icon="envelope" label="Type your email" type="email" validate name="jsUserEmail"
											success="right" onChange={handleChange} />
										<select class="form-select" aria-label="Select the cluster" onChange={handleChange} name="clusterUniqueId">
											<option selected>Select the cluster</option>
											{clusters.map((c, index) => <option value={c.uuid}>{c.name} of env {c.environment}</option>)}

										</select>

										<MDBInput containerClass="text-left" label="Key=value lable please ..." icon="pen" type="text" onChange={handleChange} name="accessedLabel" />
									</div>

									<Link to="/home">
										<MDBBtn className="position-absolute bottom-20 end-40 waves-light" gradient="purple" rounded="true" mdbWavesEffect>Cancel</MDBBtn>
									</Link>
									<MDBBtn onClick={handleSubmit} className="position-absolute bottom-20 end-0 waves-light" gradient="blue" rounded="true" mdbWavesEffect >Submit</MDBBtn>
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