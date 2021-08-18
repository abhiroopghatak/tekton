import React, { useState } from "react";
import { Link } from 'react-router-dom';
import '../../styles/componentstyles/login.css';
import DataService from '../../restapi/data-service/DataService.js';
const Login = () => {

	const loginFormData = Object.freeze({
		username: "",
		password: ""
	});
	const [formData, updateFormData] = useState(loginFormData);
	const [result, setResult] = useState([]);
	const [validForm, setValidForm] = useState([]);
	const handleChange = (e) => {

		updateFormData({
			...formData,
			[e.target.name]: e.target.value.trim()
		});
	};


	const validateForm = () => {
		if (formData.username && formData.password) {
			setValidForm('T');
			return true;
		} else {
			setValidForm('F');
			return false;
		}
	}

	const handleSubmit = (e) => {
		e.preventDefault()
		if (validateForm()) {
			DataService.loginUser(formData).then(
				(response) => {
					console.log(response);
					localStorage.setItem("_token", JSON.stringify(response.data.jwttoken));
					localStorage.setItem("_u_email", JSON.stringify(response.data.useremail));
					window.location.href = "/home";
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
					<form>

						<h3>Log in</h3>
						{validForm === 'F' ? <div id="pwd-alert" class="alert alert-danger" role="alert" >
							In valid form . Please fill data carefully.
								</div> : null}
						{result === 'F' ? <div id="pwd-alert" class="alert alert-info" role="alert" >
							Login to application failed. Please fill valid credentials.
								</div> : null}
						<div className="form-group">
							<label>Email</label>
							<input type="email" onChange={handleChange} className="form-control" name="username" placeholder="Enter email" />
						</div>

						<div className="form-group">
							<label>Password</label>
							<input type="password" onChange={handleChange} className="form-control" name="password" placeholder="Enter password" />
						</div>


						<button type="submit" onClick={handleSubmit} className="btn btn-dark btn-lg btn-block">Sign in</button>
						<p className="forgot-password text-right">
							Forgot <a href="#">password?</a>
						</p>

						<div class="alert alert-warning" role="alert">
							<span
								className="align-right">Do not have account . Please register
						here</span>
						</div>
						<Link to="/sign-up">	<button className="btn btn-dark btn-lg btn-block float-right" rounded="true" >Sign-up</button></Link>

					</form>
				</div>
			</div></div>
	);

}
export default Login;	