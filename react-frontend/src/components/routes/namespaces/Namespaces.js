
import React, { useState, useEffect, useMemo, useRef } from 'react';
import { MDBCard } from "mdbreact";
import DataService from '../../../restapi/data-service/DataService.js';

import '../../../styles/componentstyles/table.css';


import { useTable } from "react-table";

const tableHead = {
	name: "Campaign Name",
	parentId: "Campaign Id",
	campaignType: "Type",
	status: "Status",
	channel: "Channel"
};



const Namespaces = (props) => {

	const [resources, setResources] = useState([]);
	const [searchTitle, setSearchTitle] = useState("");
	const resourcesRef = useRef();
	resourcesRef.current = resources;

	useEffect( async () => {		retrieveResources();	}, []);

	const onChangeSearchTitle = (e) => {
		const searchTitle = e.target.value;
		setSearchTitle(searchTitle);
	};
	const retrieveResources = () => {
		
		DataService.getResourcePerNamespaces({clusterId: "1111",
			userId: "1111"})
			.then((response) => {
				setResources(response.data);
			})
			.catch((e) => {
				console.log(e);
			});
	};

	const refreshList = () => {
		retrieveResources();
	};



	const findByTitle = () => {
		DataService.getCostDataPerCluster(1111)
			.then((response) => {
				setResources(response.data);
			})
			.catch((e) => {
				console.log(e);
			});
	};

	const openTutorial = (rowIndex) => {
		const id = resourcesRef.current[rowIndex].id;

		props.history.push("/Resources/" + id);
	};



	const columns = useMemo(
		() => [
			{
				Header: "Namespace",
				accessor: "namespaceName",
			},
			{
				Header: "LabelSelector",
				accessor: "labelSelector",
			},
			{
				Header: "Storage",
				accessor: "storageVolume",
				
			},
			{
				Header: "cpu-quota",
				accessor: "requestCpu",
			},
			{
				Header: "cpu-used",
				accessor: "usedCpu",
			},
			{
				Header: "memory-quota",
				accessor: "requestMemory",
			},
			{
				Header: "memory-used",
				accessor: "usedMemory",
			},
			{
				Header: "Actions",
				accessor: "actions",
				Cell: (props) => {
					const rowIdx = props.row.id;
					return (
						<div>
							<span onClick={() => openTutorial(rowIdx)}>
								<i className="far fa-edit action mr-2"></i>
							</span>

							<span >
								<i className="fas fa-trash action"></i>
							</span>
						</div>
					);
				},
			},
		],
		[]
	);
	const {
		getTableProps,
		getTableBodyProps,
		headerGroups,
		rows,
		prepareRow,
	} = useTable({
		columns,
		data: resources,
	});

	return (
		<div className="list row">
			<div className="col-md-8">
				<div className="input-group mb-3">
					<input
						type="text"
						className="form-control"
						placeholder="Search by title"
						value={searchTitle}
						onChange={onChangeSearchTitle}
					/>
					<div className="input-group-append">
						<button
							className="btn btn-outline-secondary"
							type="button"
							onClick={findByTitle}
						>
							Search
            </button>
					</div>
				</div>
			</div>
			<div className="col-md-12 list">
				<table
					className="table table-striped table-bordered"
					{...getTableProps()}
				>
					<thead>
						{headerGroups.map((headerGroup) => (
							<tr {...headerGroup.getHeaderGroupProps()}>
								{headerGroup.headers.map((column) => (
									<th {...column.getHeaderProps()}>
										{column.render("Header")}
									</th>
								))}
							</tr>
						))}
					</thead>
					<tbody {...getTableBodyProps()}>
						{rows.map((row, i) => {
							prepareRow(row);
							return (
								<tr {...row.getRowProps()}>
									{row.cells.map((cell) => {
										return (
											<td {...cell.getCellProps()}>{cell.render("Cell")}</td>
										);
									})}
								</tr>
							);
						})}
					</tbody>
				</table>
			</div>

			<div className="col-md-8">
				<button className="btn btn-sm btn-danger" >
					Remove All-todelete
        </button>
			</div>
		</div>
	);
};

export default Namespaces;