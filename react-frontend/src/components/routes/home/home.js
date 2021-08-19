import { Component } from 'react';
import { Link } from "react-router-dom";
import { MDBContainer, MDBRow, MDBCol, MDBCard, MDBCardBody, MDBCardTitle, MDBListGroupItem, MDBListGroup, MDBCollapse } from "mdbreact";
import _user_icon from "../../../images/icon/_user_icon.png";

import BannerMdbRow from './bannerMdbRow.js';
import DataService from '../../../restapi/data-service/DataService.js';
import UserProfileCard from '../../ui/userProfileCard.js';

import SpinnerPage from '../../ui/spinner.js';
import ErrorAlert from '../../ui/error/errorAlert.js';
class Home extends Component {
	constructor(props) {
		super(props);
		this.state = {
			clusters: [],
			collapseID: "",
			cluster: "",
			showSpinner: true,
			isAdmin: JSON.parse(localStorage.getItem('system_role'))
		}
	}
	toggleCollapse = (collapseID, clusterid) => {
		this.setState(prevState => ({
			collapseID: prevState.collapseID !== collapseID ? collapseID : ""
		}));
		this.state.cluster = {
			"clusterId": clusterid,
			"errorMessage": ""
		};

		this.loadData();
	}


	loadData() {

		DataService.getClusterMetadata(this.state.cluster).then(

			(response) => {
				this.setState({
					cluster: response.data,
					showSpinner: false
				});

			}).catch(error => {
				this.state.cluster.errorMessage = "error occurred in api call";
				console.error('There was an error!', error);
			});

	}

	componentDidMount() {
		DataService.getAllClusters().then(

			(response) => {
				this.setState({
					clusters: response.data
				});

			}).catch(error => {
				console.log(error);
			});

	}

	render() {
		return (
			<div>
				<MDBContainer className="z-depth-5">
					<MDBRow class=".mt-30">
						<MDBCol sm="4">
							<UserProfileCard />
						</MDBCol>

						<MDBCol sm="8" className="border border-primary hoverable">
							<MDBCardTitle className="h3-responsive font-bold">List of cluster registered against your email</MDBCardTitle>
							<MDBListGroup className="my-3 mx-3">
								{this.state.clusters.map(cluster => (
									<MDBListGroupItem color="primary"> <a class="nav-link " id="v-pills-home-tab" role="tab" aria-selected="true" onClick={() => this.toggleCollapse(cluster.uuid, cluster.uuid)}
									>{cluster.name} of env: {cluster.environment} registered on {cluster.registeredon.substring(0, 10)}</a>

										<MDBCollapse id={cluster.uuid} isOpen={this.state.collapseID}> {this.state.showSpinner ?
											<SpinnerPage /> : null} {this.state.cluster.errorMessage ?
												<ErrorAlert /> : null}
											<MDBCard className="border border-info">
												<MDBCardBody className="clearfix">
													<div class="table-responsive">
														<table class="table table-success ">
															<thead>
																<tr>
																	<th scope="col">clusterName</th>
																	<th scope="col">Environment</th>
																	<th scope="col">clusterVersion</th>
																	<th scope="col">platform</th>
																	<th scope="col">channel</th>

																	<th scope="col">k8s-gitVersion</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>{this.state.cluster.clusterName}</td>
																	<td>{this.state.cluster.env}</td>
																	<td>{this.state.cluster.clusterVersion}</td>
																	<td>{this.state.cluster.platform}</td>
																	<td>{this.state.cluster.channel}</td>

																	<td>{this.state.cluster.gitVersion}</td>
																</tr>
															</tbody>
														</table>
													</div>
													<Link to="/resources">	{this.state.cluster.errorMessage ?
														<button type="button" class="btn btn-dark p-2 float-end disabled"  >Select</button> : <button type="button" class="btn btn-dark p-2 pull-right " >Select</button>}

													</Link>

												</MDBCardBody>

											</MDBCard>
										</MDBCollapse></MDBListGroupItem>))}
							</MDBListGroup>
							{this.state.isAdmin ==='AU'?
								<Link to="/add-cluster">
									<button type="button" class="btn btn-dark text-light pull-right" >Add Cluster</button>
								</Link>
								: null}
						</MDBCol>
					</MDBRow>
					<BannerMdbRow /> </MDBContainer>
			</div>
		);
	}
}
export default Home;