import React, { useState, useEffect } from "react";
import '../../styles/componentstyles/login.css';
import DataService from '../../restapi/data-service/DataService.js';


const SignUp = () => {
	const signupFormData = Object.freeze({
		email: "",
		fullname: "",
		pwd: "",
		confirmcode: ""
	});
	const [formData, updateFormData] = useState(signupFormData);
	const [result, setResult] = useState([]);
	const [validPasscode, setValidPasscode] = useState(['T']);


	const handleChange = (e) => {

		updateFormData({
			...formData,
			[e.target.name]: e.target.value.trim()
		});
	};


	const validateForm = () => {
		if (formData.pwd != formData.confirmCode) {
			setValidPasscode('F');
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

					{(result === 'F') ? <div id="success-alert" class="alert alert-warning" role="alert" >
						Request couldnt be raised due to data error . Please try later!!!
								</div> : ''}


					<form>
						<h3>Register</h3>

						<div className="form-group">
							<label>Full name</label>
							<input type="text" name="fullname" className="form-control" onChange={handleChange} placeholder="[First name] [middle name] [lastname]" />
						</div>


						<div className="form-group">
							<label>Email</label>
							<input name="email" type="email" className="form-control" onChange={handleChange} placeholder="Enter email" />
						</div>

						<div className="form-group">
							<label>Password</label>
							<input type="password" name="pwd" className="form-control" onChange={handleChange} placeholder="Enter password" />
						</div>


						<div className="form-group">
							<label>Confirm Password</label>
							<input type="password" name="confirmCode" className="form-control" onChange={handleChange} placeholder="Type password again" />
						</div>
						{validPasscode === 'F' ? <div id="pwd-alert" class="alert alert-danger" role="alert" >
							Both password doesnt match . Please try carefully.
								</div> : null}

						<button type="submit" onClick={handleSubmit} className="btn btn-dark btn-lg btn-block">Register</button>
						<p className="forgot-password text-right">
							Already registered <a href="/sign-in">log in?</a>
						</p>
					</form>
				</div>
			</div></div>
	);
}

export default SignUp;	