import React, { Component } from 'react';

export class ClusterInfoDetails extends Component {

	
	continue = e => {
		e.preventDefault();
			this.props.nextStep();

	};


	render() {
		const { values, handleChange } = this.props;
		const isEnabled = values.name.length > 0 && values.endpoint.length > 0 && values.environment.length >0 && values.token.length>0;
		return (<>

			<div className="outer">
				<div className="inner">

					<h4>Add cluster information to system</h4>

					

					<div className="form-group required">
						<label className="control-label">CLuster Name</label>
						<input type="text" name="name" className="form-control " onChange={handleChange('name')} defaultValue={values.name} placeholder="Cluster unique name" />
					</div>


					<div className="form-group required">
						<label className="control-label">Cluster End Point</label>
						<input name="endpoint" type="url" className="form-control" onChange={handleChange('endpoint')} defaultValue={values.endpoint} placeholder="api end point" />
					</div>

					<div className="form-group required">
						<label className="control-label">Environment</label>
						<input type="text" name="environment" className="form-control" onChange={handleChange('environment')} defaultValue={values.environment} placeholder="Enter Dev / Stage / PROD  etc." />
					</div>


					<div className="form-group required">
						<label className="control-label">Service Account Token</label>
						<textarea type="text" name="token" className="form-control" onChange={handleChange('token')} defaultValue={values.token} placeholder="account token to authenticate" />
					</div>

					<br />
					<button type="button" style={{textTransform: 'none'}} onClick={this.continue} disabled={!isEnabled} className="btn btn-dark btn-lg btn-block">Continue</button>

				</div></div>

		</>);
	}
}

export default ClusterInfoDetails;