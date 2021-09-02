import React, { useState, useEffect } from "react";
import '../../styles/componentstyles/login.css';
import DataService from '../../restapi/data-service/DataService.js';
import ErrorAlert from '../ui/error/errorAlert.js';

const SignUp = () => {
	const signupFormData = Object.freeze({
		email: "",
		fullname: "",
		pwd: "",
		confirmCode: ""
	});
	const [formData, updateFormData] = useState(signupFormData);
	const [result, setResult] = useState([]);
	const [validPasscode, setValidPasscode] = useState(['T']);
	const [errMsg, setErrMsg] = useState([]);


	const handleChange = (e) => {

		updateFormData({
			...formData,
			[e.target.name]: e.target.value.trim()
		});
	};


	const validateForm = () => {
		if (formData.pwd != formData.confirmCode) {
			setValidPasscode('F');
			setErrMsg('Both password doesnt match . Please try carefully.');
			return false;
		} else if (!(formData.fullname && formData.email && formData.pwd && formData.confirmCode)) {
			setValidPasscode('F');
			setErrMsg('One of form field is empty . Please fill carefully.');
			return false;

		} else {
			return true;
		}
	}
	const handleSubmit = (e) => {
		e.preventDefault()
		if (validateForm()) {
			DataService.registerUser(formData).then(
				(response) => {
					console.log(response.data);
					setResult(response.data.message);
				}).catch((error) => {
					setResult('F');
					setErrMsg('Request couldnt be raised due to data error . Please try later!!!');
					console.log(error);
				});

		}
	};


	return (


		<div className="outer">
			<div className="inner">
				<div className="login-box">
					{(result === 'S') ?
						<div id="success-alert" class="alert alert-success" role="alert" >
							Resquest has been raised successfully. Please wait for confirmation mail from admin.
								</div> : ''}

					{(result === 'F') ?<ErrorAlert msg={errMsg} /> : null}


					<form>
						<h3>Register</h3>

						<div className="form-group required">
							<label className="control-label">Full name</label>
							<input type="text" name="fullname" className="form-control" onChange={handleChange} placeholder="[First name] [middle name] [lastname]" />
						</div>


						<div className="form-group required">
							<label className="control-label">Email</label>
							<input name="email" type="email" className="form-control" onChange={handleChange} placeholder="Enter email" />
						</div>

						<div className="form-group required">
							<label className="control-label">Password</label>
							<input type="password" name="pwd" className="form-control" onChange={handleChange} placeholder="Enter password" />
						</div>


						<div className="form-group required">
							<label className="control-label">Confirm Password</label>
							<input type="password" name="confirmCode" className="form-control" onChange={handleChange} placeholder="Type password again" />
						</div>
						{validPasscode === 'F' ? <ErrorAlert msg={errMsg} /> : null}

						<button type="submit" style={{textTransform: 'none'}} onClick={handleSubmit} className="btn btn-dark btn-lg btn-block">Register</button>
						<p className="forgot-password text-right">
							Already registered <a href="/login">log in?</a>
						</p>
					</form>
				</div>
			</div></div>
	);
}

export default SignUp;	