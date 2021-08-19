import React, { useState, useEffect } from "react";

import DataService from '../../../restapi/data-service/DataService.js';
import ErrorAlert from '../../ui/error/errorAlert.js';

const AddCluster = () => {


	const addClusterFormData = Object.freeze({
		name: "",
		endpoint: "",
		token: "",
		environment: ""
	});
	const [formData, updateFormData] = useState(addClusterFormData);
	const [result, setResult] = useState([]);
	const [validFOrm, setValidFOrm] = useState([]);
	const [errMsg, setErrMsg] = useState([]);

	const handleChange = (e) => {

		updateFormData({
			...formData,
			[e.target.name]: e.target.value.trim()
		});
	};


	const validateForm = () => {

		return true;
	}
	const handleSubmit = (e) => {
		e.preventDefault()
		if (validateForm()) {
			DataService.addCluster(formData).then(
				(response) => {
					console.log(response.data);
					setResult(response.data.message);
				}).catch((error) => {
					setResult('F');
					console.log(error);
				});

		}
	};


	return (<div className="outer">
		<div className="inner">
			<div className="login-box">
				{(result === 'S') ?
					<div id="success-alert" class="alert alert-success" role="alert" >
						Resquest has been raised successfully. Please wait for confirmation mail from admin.
								</div> : ''}

				{(result === 'F') ? <div id="success-alert" class="alert alert-warning" role="alert" >
					Request couldnt be raised due to data error . Please try later!!!
								</div> : ''}


				<form>
					<h3>Add cluster information to system</h3>

					<div className="form-group">
						<label>CLuster Name</label>
						<input type="text" name="name" className="form-control" onChange={handleChange} placeholder="Cluster unique name" />
					</div>


					<div className="form-group">
						<label>Cluster End Point</label>
						<input name="endpoint" type="url" className="form-control" onChange={handleChange} placeholder="api end point" />
					</div>

					<div className="form-group">
						<label>Environment</label>
						<input type="text" name="environment" className="form-control" onChange={handleChange} placeholder="Enter Dev / Stage / PROD  etc." />
					</div>


					<div className="form-group">
						<label>Service Account Token</label>
						<input type="text" name="token" className="form-control" onChange={handleChange} placeholder="account token to authenticate" />
					</div>
					{errMsg === 'F' ? <ErrorAlert msg={errMsg} /> : null}

					<button type="submit" onClick={handleSubmit} className="btn btn-dark btn-lg btn-block">Enter</button>
					
				</form>
			</div>
		</div></div>
	);
}

export default AddCluster;