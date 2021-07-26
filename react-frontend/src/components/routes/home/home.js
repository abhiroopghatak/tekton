import React from "react";


import { MDBJumbotron, MDBIcon, MDBBtn, MDBContainer, MDBRow, MDBCol, MDBCard, MDBCardBody, MDBCardImage, MDBCardTitle, MDBCardText, MDBListGroupItem, MDBListGroup } from "mdbreact";
import bgimage from "../../../images/home-jumbotron.PNG";
import _user_icon from "../../../images/icon/_user_icon.png";


import ClusterDataService from '../../../restapi/data-service/clusterDataService';


class Home extends React.Component {


	constructor(props) {
		super(props)
		this.state = {
			clusters: []
		}
	}

	componentDidMount() {
		ClusterDataService.getAllClusters().then(

			(response) => {
				this.setState({ clusters: response.data });

			}).catch(error => {
				console.log(error);
			});

	}


	render() {
		return (
			<div>
				<MDBContainer>
					<MDBRow>
						<MDBCol sm="12">
							<MDBJumbotron style={{ padding: 0 }}>
								<MDBCol className="text-black text-center py-5 px-4 my-5" style={{ backgroundImage: `url(https://mdbcdn.b-cdn.net/img/Photos/Others/gradient1.jpg)` }}>
									<MDBCol className="py-12">
										<MDBCardTitle className="h1-responsive pt-3 m-5 font-bold">Verify kubernetes cluster usage and reduce cluster spending</MDBCardTitle>
										<p className="mx-5 mb-5 ">Kubetime provides realtime kubernetes cluster usage and generates time based spending . Adding to that it can facilitate as advisor which can suggest house keeping opertaion to recduce cluster spending.
                </p>

									</MDBCol>
								</MDBCol>
							</MDBJumbotron>
						</MDBCol>
					</MDBRow>

					<MDBRow>
						<MDBCol sm="8" className="border border-primary" >

							<MDBCardTitle className="h1-responsive font-bold">List of cluster registered against your email</MDBCardTitle>
							<MDBListGroup className="my-3 mx-3" >
								{
									this.state.clusters.map(cluster => (

										<MDBListGroupItem color="primary"><a
											class="nav-link "
											id="v-pills-home-tab"
											data-mdb-toggle="pill"
											href="#v-pills-home"
											role="tab"
											aria-controls="v-pills-home"
											aria-selected="true"
										>{cluster.name}</a></MDBListGroupItem>
									))}

							</MDBListGroup>
						</MDBCol>


						<MDBCol sm="4" >
							<MDBCard className="border border-info">
								<MDBCardBody>
									<img className="img-fluid" src={_user_icon} width="193" height="130" margin="auto" />
									<MDBCardTitle>Welcome User</MDBCardTitle>
									<MDBCardText>
										You have been logged in as user@email.com and below are relevant details.
									user name <br />
									user status
          						</MDBCardText>
								</MDBCardBody>
							</MDBCard>
						</MDBCol>
					</MDBRow>


				</MDBContainer>


			</div >
		);
	}
}
export default Home;