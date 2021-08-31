
import React, { useState } from "react";



const ErrorAlert = (props) => {

	
	return (

			<div class="alert alert-danger alert-dismissible fade show" role="alert">
				<strong>Unexpected !</strong>
				{props.msg ? props.msg : 'This is an error occured in api calls. Can not display data at this moment.'}

				
			</div>
	);
}

export default ErrorAlert;