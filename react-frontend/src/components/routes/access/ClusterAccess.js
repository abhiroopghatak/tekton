import React, { useState, useEffect } from 'react';


import { Link } from 'react-router-dom';
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
				setResult(response.data.message);
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
					<div class="container">
						<div class="row">
							<div class="col" >
								{(result === 'S') ?
									<div id="success-alert" class="alert alert-success" role="alert" >
										Resquest has been raised successfully
								</div> : ''}

								{(result === 'F') ? <div id="success-alert" class="alert alert-warning" role="alert" >
									Request couldnt be raised due to data error . Please connect Admin!!!
								</div> : ''}
								<form onSubmit={handleSubmit}>
									<p className="h4 text-center">Cluster Access Request</p>
									<div class="input-group mb-3 ">

										<input class="w-100" placeholder="Type your email" type="text" validate name="jsUserEmail"
											onChange={handleChange} />
										<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
									</div>
									<div class="form-group input-group-lg mb-3">
										<select class="form-select" aria-label="Select the cluster" onChange={handleChange} name="clusterUniqueId">
											<option selected>Select the cluster</option>
											{clusters.map((c, index) => <option value={c.uuid}>{c.name} of env {c.environment}</option>)}

										</select>
									</div>

									<div class="input-group mb-3">
										<input class="w-100" placeholder="Key=value lable please ..." type="text" onChange={handleChange} name="accessedLabel" />
									</div>
									<small id="labelHelp" class="form-text text-muted">provide the label selector based on that namespaces in that selected cluster will appear in your dashboard</small>
									<br />
									<br />
									<div class="button-group mb-3">
										<Link to="/home">
											<button className="btn btn-info float-left"  rounded="true" >Cancel</button>
										</Link>
										<button type="button" onClick={handleSubmit} className="btn btn-primary float-right" rounded="true"  >Submit</button>
									</div>
								</form>
							</div>


						</div>

					</div>

				</div>
			</div>
		</div>

	);
};

export default ClusterAccess;	