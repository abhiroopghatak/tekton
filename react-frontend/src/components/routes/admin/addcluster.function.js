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
	const [validForm, setValidForm] = useState([]);
	const [errMsg, setErrMsg] = useState([]);

	const handleChange = (e) => {

		updateFormData({
			...formData,
			[e.target.name]: e.target.value.trim()
		});
	};


	const validateForm = () => {
		if (!(formData.name && formData.endpoint && formData.token && formData.environment)) {
			setValidForm('F');
			setErrMsg('One of form field is empty . Please fill carefully.');
			return false;
		}
		return true;
	}
	const handleSubmit = (e) => {
		e.preventDefault()
		if (validateForm()) {
			DataService.addCluster(formData).then(
				(response) => {
					console.log(response.data);
					setResult(response.data.message);
					if (response.data.status !=200) {
						setResult('F');
						setErrMsg(response.data.obj.errorMessage);
					}
					setResult(response.data.message);
				}).catch((error) => {
					setResult('F');
					setErrMsg('Data couldnt be saved in to system due to api error . Please try later!!!');
					console.log(error);
				});

		}
	};


	return (<div className="outer">
		<div className="inner">
			<div className="login-box">

				<h3>Add cluster information to system</h3>
				{(result === 'S') ?
					<div id="success-alert" class="alert alert-success alert-dismissible fade show" role="alert" >
						New cluster has been added in to the system . 
								</div> : ''}

				{(result === 'F') ? <ErrorAlert msg={errMsg} /> : null}


				<form>

					{validForm === 'F' ? <ErrorAlert msg={errMsg} /> : null}

					<div className="form-group required">
						<label className="control-label">Cluster Name</label>
						<input type="text" name="name" className="form-control " onChange={handleChange} placeholder="Cluster unique name" />
					</div>


					<div className="form-group required">
						<label className="control-label">Cluster End Point</label>
						<input name="endpoint" type="url" className="form-control" onChange={handleChange} placeholder="api end point" />
					</div>

					<div className="form-group required">
						<label className="control-label">Environment</label>
						<input type="text" name="environment" className="form-control" onChange={handleChange} placeholder="Enter Dev / Stage / PROD  etc." />
					</div>


					<div className="form-group required">
						<label className="control-label">Service Account Token</label>
						<textarea type="text" name="token" className="form-control" onChange={handleChange} placeholder="account token to authenticate" />
					</div>


					<button type="submit" onClick={handleSubmit} className="btn btn-dark btn-lg btn-block">Enter</button>

				</form>
			</div>
		</div></div>
	);
}

export default AddCluster;