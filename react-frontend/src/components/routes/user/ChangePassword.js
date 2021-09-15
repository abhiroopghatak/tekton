import React, { useState } from "react";

import DataService from '../../../restapi/data-service/DataService.js';
import ErrorAlert from '../../ui/error/errorAlert.js';


const ChangePassword = () => {
	const cpFormData = Object.freeze({
		oldpassword: "",
		newpassword: "",
		confirmcode: "",
		useremail: ""
	});

	const [formData, updateFormData] = useState(cpFormData);
	const [result, setResult] = useState([]);
	const [errMsg, setErrMsg] = useState([]);

	const handleChange = (e) => {

		updateFormData({
			...formData,
			[e.target.name]: e.target.value.trim()
		});
	};

	const validateForm = () => {
		if (formData.newpassword !== formData.confirmcode) {
			setResult('F');
			setErrMsg('Both password doesnt match . Please try carefully.');
			return false;
		} else if (!(formData.oldpassword && formData.newpassword && formData.confirmcode)) {
			setResult('F');
			setErrMsg('One of form field is empty . Please fill carefully.');
			return false;

		} else {
			return true;
		}
	}

	const handleSubmit = (e) => {
		e.preventDefault();
		if (validateForm()) {
			var _u_email = JSON.parse(localStorage.getItem('_u_email'));

			formData.useremail = _u_email;
			DataService.changePassword(formData).then((response) => {
				console.log(response.data);

				if (response.data.status !== 'OK') {
					setResult('F');
					setErrMsg(response.data.obj);
				}
				setResult(response.data.message);
				localStorage.removeItem("_token");
				localStorage.clear();
			}).catch((error) => {
				setResult('F');
				setErrMsg('Request couldnt be completed due to data error . Please try later!!!');
				console.log(error);
			});
		}
	};
	const isEnabled = formData.oldpassword.length > 0 && formData.newpassword.length > 0 && formData.confirmcode.length > 0;
	return (


		<div className="outer">
			<div className="inner">
				<div className="login-box">
					<h4> Change Password </h4>


					{result === 'F' ? <ErrorAlert msg={errMsg} /> : null}
					{(result === 'S') ?
						<div id="success-alert" class="alert alert-success" role="alert" >
							Password has been updated successfully <a href="/login"> Try Re-Login with New Password </a>
								</div> : ''}

					<div className="form-group required">
						<label className="control-label">Old Password</label>
						<input type="text" name="oldpassword" className="form-control" onChange={handleChange} placeholder="Enter Old password" />
					</div>

					<div className="form-group required">
						<label className="control-label">New Password</label>
						<input type="password" name="newpassword" className="form-control" onChange={handleChange} placeholder="Enter New password" />
					</div>


					<div className="form-group required">
						<label className="control-label">Confirm Password</label>
						<input type="password" name="confirmcode" className="form-control" onChange={handleChange} placeholder="Type password again" />
					</div>

					<button type="submit" style={{ textTransform: 'none' }} disabled={!isEnabled} onClick={handleSubmit} className="btn btn-dark btn-lg btn-block">Submit</button>
				</div></div></div>
	);

}

export default ChangePassword;