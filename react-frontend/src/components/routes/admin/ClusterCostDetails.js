import React, { Component } from 'react';


export class ClusterCostDetails extends Component {
	continue = e => {
		e.preventDefault();
		this.props.nextStep();
	};

	back = e => {
		e.preventDefault();
		this.props.prevStep();
	};

	render() {
		const { values, handleChange } = this.props;
		const isEnabled = values.cpucost.length > 0 && values.memorycost.length > 0 && values.storagecost.length >0 ;
		return (<>

			<div className="outer">
				<div className="inner">

					<h4>Add Cluster Resources Cost Detail </h4>

					<div className="form-group required">
						<label className="control-label">Per VCpu Cost</label>
						<input type="number" name="cpucost" className="form-control " onChange={handleChange('cpucost')} defaultValue={values.cpucost} placeholder="CPU cost" />
					</div>


					<div className="form-group required">
						<label className="control-label">Per Gi Memory Cost</label>
						<input name="memorycost" type="number" className="form-control" onChange={handleChange('memorycost')} defaultValue={values.memorycost} placeholder="Per Gi Memory Cost" />
					</div>

					<div className="form-group required">
						<label className="control-label">Storage cost</label>
						<input type="number" name="storagecost" className="form-control" onChange={handleChange('storagecost')} defaultValue={values.storagecost} placeholder="Per Gi Storage Cost" />
					</div>



					<br />
					<div class="button-group mb-3">
						<button type="button" onClick={this.back} className="btn btn-light float-left" rounded="true">Back</button>
						<button type="button" onClick={this.continue} disabled={!isEnabled} className="btn btn-dark float-right" rounded="true">Continue</button>
					</div>
				</div></div>

		</>);
	}
}

export default ClusterCostDetails;