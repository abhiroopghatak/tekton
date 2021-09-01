import React, { useState, useEffect, useRef } from "react";
import Modal from 'react-modal';
import DataService from '../../../restapi/data-service/DataService.js';
import ErrorAlert from '../../ui/error/errorAlert.js';

const AccessApproval = () => {


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
	const [clusters, setClusters] = useState([]);
	const [modalData, setModalData] = useState([]);
	const clustersRef = useRef();
	clustersRef.current = clusters;

	const [modalIsOpen, setIsOpen] = React.useState(false);
	const modalStyles = {
		content: {
			top: '10%',
			left: '30%',
			width: '50%',
			right: 'auto',
			bottom: 'auto',
		}
	};
	function openModal() {
		setIsOpen(true);
	}

	function afterOpenModal() {
		// references are now sync'd and can be accessed.
	}

	function closeModal() {
		setIsOpen(false);
	}
	const openActionDialog = (rowIndex) => {
		setModalData(clustersRef.current[rowIndex]);
		openModal();

	};

	useEffect(async () => { loadData(); }, []);
	const loadData = () => {
		DataService.getRequestedAccess().then(
			(response) => {
				setClusters(response.data);

			}).catch((error) => {
				console.log(error);
			});

	};


	const handleReject = (e) => {
		e.preventDefault();
		DataService.rejectCLusterAccess(modalData).then(
			(response) => {
				console.log(response.data);
				setResult(response.data.message);
				if (result === 'F') {
					setErrMsg("Data conflict occurred and statuc couldnt be updated.");
				} else if (result === 'S') {
					loadData();
					closeModal();
				}

			}).catch((error) => {
				setResult('F');
				setErrMsg('Data couldnt be saved in to system due to api error . Please try later!!!');
				console.log(error);
			});
	};
	const handleAccept = (e) => {
		e.preventDefault();
		DataService.approveCLusterAccess(modalData).then(
			(response) => {
				console.log(response.data);
				setResult(response.data.message);
				if (result === 'F') {
					setErrMsg("Data conflict occurred and statuc couldnt be updated.");
				}
				else if (result === 'S') {
					loadData();
					closeModal();
				}
			}).catch((error) => {
				setResult('F');
				setErrMsg('Data couldnt be saved in to system due to api error . Please try later!!!');
				console.log(error);
			});

	};


	return (

		<div className="outer">
			<div className="inner-broad">
				<h2>AccessApproval</h2>
				{(result === 'S') ?
					<div id="success-alert" class="alert alert-success alert-dismissible fade show" role="alert" >
						Status has been updated in system.
								</div> : ''}

				<table class="table  table-striped table-hover">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">UserEmail</th>
							<th scope="col">Cluser Name</th>
							<th scope="col">Environment</th>
							<th scope="col">Label Requested</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>

						{clusters.map((cluster, index) => (

							<tr>
								<td scope="row">{index + 1}</td>
								<td>{cluster.useremail}</td>
								<td>{cluster.clusterName}</td>
								<td>{cluster.environment}</td>
								<td>{cluster.requestedLabel}</td>

								<td>
									<a onClick={() => openActionDialog(index)}>
										<span >
											<i className="far fa-edit action mr-2"></i>
										</span>

									</a>
								</td>
							</tr>

						))}

					</tbody>
				</table>

				<Modal
					isOpen={modalIsOpen}
					style={modalStyles}
					onRequestClose={closeModal}
				>
					<div class="modal-header">
						<h5 class="modal-title" id="actionModal">Select the action to perform</h5>

						<button type="button" style={{textTransform: 'none'}} class="btn btn-dark" onClick={closeModal}>Close</button>
					</div>
					<div class="modal-body">
						{(result === 'F') ? <ErrorAlert msg={errMsg} /> : null}
						<table>
							<tr>
								<th class="small text-muted pr-2" scope="row">User Email</th>
								<td>{modalData.useremail}</td>
							</tr>
							<tr>
								<th class="small text-muted pr-2" scope="row">Cluster Name</th>
								<td>{modalData.clusterName}</td>
							</tr>
							<tr>
								<th class="small text-muted pr-2" scope="row">Requested Label</th>
								<td>{modalData.requestedLabel}</td>
							</tr>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" style={{textTransform: 'none'}} class="btn btn-warning pull-left" onClick={handleReject}>Reject</button>
						<button type="button" style={{textTransform: 'none'}} class="btn btn-success" onClick={handleAccept}>Accept</button>
					</div>
				</Modal>

			</div></div>
	);
}

export default AccessApproval;