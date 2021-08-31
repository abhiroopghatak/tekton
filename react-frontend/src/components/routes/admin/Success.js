import React, { Component } from 'react';
import { Link } from 'react-router-dom';


export class Success extends Component {



	render() {
		return (
			<>
				<div className="outer">
					<div className="inner">

						<h4>Thank You For Your Submission</h4>
						<p>User will see the entry in accessble cluster  list</p>




						<div class="button-group mb-3">
							
							<Link to="/home">
								<button type="button" className="btn btn-dark float-right">Go to Home</button>
							</Link>
						</div>
					</div></div>
			</>
		);
	}
}

export default Success;