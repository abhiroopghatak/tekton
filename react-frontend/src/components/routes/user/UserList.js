import React, { useState, useEffect, useRef } from 'react';
import DataService from '../../../restapi/data-service/DataService.js';
import Modal from 'react-modal';
const UserList = () => {

	const [modalData, setModalData] = useState([]);
	const [modalIsOpen, setIsOpen] = React.useState(false);
	const [userList, setUserList] = useState([]);
	const usersRef = useRef();
	usersRef.current = userList;


	useEffect(() => {
		loadData();

	}, []);

	async function loadData() {

		DataService.getJsUserDataList().then(
			(response) => {
				setUserList(response.data);
				console.log(response.data);
			}).catch((error) => {
				console.log(error);
			});
	}

	const modalStyles = {
		overlay: {
			backgroundColor: 'rgba(0,0,0,0.5)',
		},
		content: {
			top: '20%',
			left: '25%',
			width: '50%',
			right: 'auto',
			bottom: 'auto',
		}
	};
	const openDetailDialog = (rowIndex) => {
		setModalData(usersRef.current[rowIndex]);
		openModal();

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

	return (<>

		<div className="outer">
			<div className="inner-broad">

				<h4> List of registered User</h4>
				<table class="table  table-striped table-hover">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">UserEmail</th>
							<th scope="col">User Name</th>
							<th scope="col">Role</th>
							<th scope="col">Last Updated at</th>
							<th scope="col">Status</th>
							<th scope="col">Access Detail</th>

						</tr>


					</thead>
					<tbody>
						{userList.length > 0 ?
							<>

								{userList.map((user, index) => (

									<tr>
										<td scope="row">{index + 1}</td>
										<td>{user.email}</td>
										<td>{user.fullname}</td>
										<td>{user.role === 'AU' ? "Admin" : "End User"}</td>
										<td>{user.lastUpdated}</td>
										<td>{user.status === 'I' ? "InActive" : (user.status === 'A')? "Active":"Registered"}</td>

										<td>
											<a onClick={() => openDetailDialog(index)}>
												<span >
													<i className="far fa-edit action mr-2"></i>
												</span>

											</a>
										</td>
									</tr>

								))}

							</> : <p>"No Data Available" </p>}

					</tbody>
				</table>



				<Modal
					isOpen={modalIsOpen}
					style={modalStyles}
					onRequestClose={closeModal}
				>
					<div class="modal-header">
						<h4 class="modal-title">{modalData.fullname}'s cluster Access</h4>
						<button type="button" class="btn btn-secondary" style={{ textTransform: 'none' }} onClick={closeModal}>Close</button>
					</div>
					<div class="modal-body">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Cluster name</th>
									<th scope="col">Accessed Label</th>
									<th scope="col">Label Access Status</th>
								</tr>
							</thead>
							<tbody>
							
							{(modalData.calist &&modalData.calist.length > 0  )? <>{modalData.calist.map((ca, idx) => (
										<tr>
											
											<td>{ca.clustername}</td>
											<td>{ca.labelSelector}</td>
											<td>{ca.status}</td>
										</tr>))}</> : <td>"No Access Data avaialble"</td>}
								
									
							</tbody>
						</table>

					</div>
				</Modal>
			</div></div>

	</>);
};

export default UserList;