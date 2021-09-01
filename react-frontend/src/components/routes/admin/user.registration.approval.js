import React, { useState, useEffect, useRef } from "react";
import Modal from 'react-modal';
import DataService from '../../../restapi/data-service/DataService.js';
import ErrorAlert from '../../ui/error/errorAlert.js';

const NewUserApproval = () => {


	
	const [result, setResult] = useState([]);
	const [errMsg, setErrMsg] = useState([]);
	const [users, setUsers] = useState([]);
	const [modalData, setModalData] = useState([]);
	const usersRef = useRef();
	usersRef.current = users;

	const [modalIsOpen, setModalIsOpen] = React.useState(false);
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
		setModalIsOpen(true);
	}

	function afterOpenModal() {
		// references are now sync'd and can be accessed.
	}

	function closeModal() {
		setModalIsOpen(false);
	}
	const openActionDialog = (rowIndex) => {
		setModalData(usersRef.current[rowIndex]);
		openModal();

	};

	useEffect(async () => { loadData(); }, []);


	const loadData = () => {
		DataService.getNewUserList().then(
			(response) => {
				setUsers(response.data);

			}).catch((error) => {
				console.log(error);
			});
	};

	const actionTaken = (yesOrNo) => {
		var data = { uuid: modalData.uuid, action: yesOrNo, message: "approval" };
		DataService.newUserApproval(data).then(
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
				<h4>Manage Users</h4>
				{(result === 'S') ?
					<div id="success-alert" class="alert alert-success alert-dismissible fade show" role="alert" >
						User state has been approved in the system.
								</div> : ''}

				<table class="table  table-striped table-hover">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">UserEmail</th>
							<th scope="col">User Name</th>
							<th scope="col">Registered Date</th>
							<th scope="col">Status</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
					
					{users.length > 0 ? 
					<>
					
					{users.map((user, index) => (

							<tr>
								<td scope="row">{index + 1}</td>
								<td>{user.email}</td>
								<td>{user.fullname}</td>
								<td>{user.createTime}</td>
								<td>{user.status=== 'I' ? "InActive" : "Registered"}</td>

								<td>
									<a onClick={() => openActionDialog(index)}>
										<span >
											<i className="far fa-edit action mr-2"></i>
										</span>

									</a>
								</td>
							</tr>

						))}
					</>
					: <p >  There is no users left in Registered or Inactive state.</p>}

						

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
								<td>{modalData.email}</td>
							</tr>
							<tr>
								<th class="small text-muted pr-2" scope="row">user Name</th>
								<td>{modalData.fullname}</td>
							</tr>
							<tr>
								<th class="small text-muted pr-2" scope="row">Registered Date</th>
								<td>{modalData.createTime}</td>
							</tr>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" style={{textTransform: 'none'}} class="btn btn-warning pull-left" onClick={()=>actionTaken('N')}>Reject</button>
						<button type="button" style={{textTransform: 'none'}} class="btn btn-success" onClick={()=>	actionTaken('Y')}>Accept</button>
					</div>
				</Modal>

			</div></div>
	);
}

export default NewUserApproval;