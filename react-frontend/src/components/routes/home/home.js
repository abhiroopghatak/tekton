import  {Component,Suspense} from "react";

import {   MDBContainer, MDBRow, MDBCol, MDBCard, MDBCardBody, MDBCardImage, MDBCardTitle, MDBCardText, MDBListGroupItem, MDBListGroup,MDBCollapse } from "mdbreact";
import _user_icon from "../../../images/icon/_user_icon.png";


import ClusterDataService from '../../../restapi/data-service/clusterDataService';

class Home extends Component {


	constructor(props) {
	    super(props);
	    this.state = {
	        clusters: [],
	        collapseID: "",
	        cluster: ""
	    }
	}
	toggleCollapse =  (collapseID,clusterid ) => {
	    this.setState(prevState => ({
	        collapseID: prevState.collapseID !== collapseID ? collapseID : ""
	    }));
	    this.state.cluster={"clusterId":clusterid};
	    
	    this.loadData();
	}
	
	
	  loadData() {
		
	    ClusterDataService.getClusterMetadata(this.state.cluster).then(
	
	        (response) => {
	            this.setState({
	                cluster: response.data
	            });
	
	        }).catch(error => {
	        console.error('There was an error!', error);
	    });
	
	}
	
	componentDidMount() {
	    ClusterDataService.getAllClusters().then(
	
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
		   <MDBContainer>
		      
		      <MDBRow class=".mt-30">
		       <MDBCol sm="3" >
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
		         <MDBCol sm="9" className="border border-primary" >
		            <MDBCardTitle className="h3-responsive font-bold">List of cluster registered against your email</MDBCardTitle>
		            <MDBListGroup className="my-3 mx-3" >
		               {
		               this.state.clusters.map(cluster => (
		               <MDBListGroupItem color="primary" >
		                  <a
		                     class="nav-link "
		                     id="v-pills-home-tab"
		                     role="tab"
		                     aria-selected="true" onClick={() =>this.toggleCollapse("basicCollapse",cluster.uuid)}
		                  >{cluster.name} registered on {cluster.registeredon.substring(0, 10)}</a>
		               </MDBListGroupItem>
		               ))}
		               <MDBCollapse id="basicCollapse" isOpen={this.state.collapseID}>
		                  <Suspense fallback={
		                  <p>Loading Cluster Details...</p>
		                  }> <>
		                  <h2>hi</h2>
		                  <p>
		                     ========================{this.state.cluster.errorMessage}================
		                  </p>
		                  </></Suspense>
		               </MDBCollapse>
		            </MDBListGroup>
		         </MDBCol>
		        
		      </MDBRow>
		      <MDBRow>
		         <MDBCol sm="12">
		            <MDBCol className="text-black text-center py-5 px-4 my-5" style={{ backgroundImage: `url(https://mdbcdn.b-cdn.net/img/Photos/Others/gradient1.jpg)` }}>
		            <MDBCol className="py-9">
		               <MDBCardTitle className="h1-responsive m-5 font-bold">Verify kubernetes cluster usage and reduce cluster spending</MDBCardTitle>
		               <p className="mx-5 mb-5 ">Kubetime provides realtime kubernetes cluster usage and generates time based spending . Adding to that it can facilitate as advisor which can suggest house keeping opertaion to recduce cluster spending.
		               </p>
		            </MDBCol>
		         </MDBCol>
		         </MDBCol>
		      </MDBRow>
		   </MDBContainer>
		</div >
		);
	}
}
export default Home;