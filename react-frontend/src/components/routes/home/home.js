import  {Component} from "react";

import {   MDBContainer, MDBRow, MDBCol, MDBCard, MDBCardBody, MDBCardImage, MDBCardTitle, MDBCardText, MDBListGroupItem, MDBListGroup,MDBCollapse } from "mdbreact";
import _user_icon from "../../../images/icon/_user_icon.png";

import BannerMdbRow from './bannerMdbRow.js';
import ClusterDataService from '../../../restapi/data-service/clusterDataService';
import SpinnerPage from '../../ui/spinner.js';
import ErrorAlert from '../../ui/errorAlert.js';
class Home extends Component {


	constructor(props) {
	    super(props);
	    this.state = {
	        clusters: [],
	        collapseID: "",
	        cluster: "",
	        showSpinner: true
	    }
	}
	toggleCollapse =  (collapseID,clusterid ) => {
	    this.setState(prevState => ({
	        collapseID: prevState.collapseID !== collapseID ? collapseID : ""
	    }));
	    this.state.cluster={"clusterId":clusterid,"errorMessage":""};
	    
	    this.loadData();
	}
	
	
	  loadData() {
		
	    ClusterDataService.getClusterMetadata(this.state.cluster).then(
	
	        (response) => {
	            this.setState({
	                cluster: response.data,
	                showSpinner : false
	            });
	
	        }).catch(error => {
		this.state.cluster.errorMessage= "error occurred in api call";
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
		                  
		                    { this.state.showSpinner ? <SpinnerPage/> : null }
		                    { this.state.cluster.errorMessage ? <ErrorAlert/> : null }
		                    <MDBCard className="border border-info">
		               <MDBCardBody>
		               <div class="table-responsive">
		                  <table class="table table-dark ">
  <thead>
    <tr>
      <th scope="col">clusterName</th>
      <th scope="col">clusterVersion</th>
      <th scope="col">platform</th>
      <th scope="col">channel</th>
      <th scope="col">buildDate</th>
      <th scope="col">k8s-gitVersion</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>{this.state.cluster.clusterName}</td>
      <td>{this.state.cluster.clusterVersion}</td>
      <td>{this.state.cluster.platform}</td>
      <td>{this.state.cluster.channel}</td>
      <td>{this.state.cluster.buildDate}</td>
      <td>{this.state.cluster.gitVersion}</td>
    </tr>
    </tbody></table></div>
		               </MDBCardBody><button type="button" class="btn btn-success"
								data-dismiss="modal">Select</button>
		            </MDBCard>
		                  
		                  
		               </MDBCollapse>
		            </MDBListGroup>
		         </MDBCol>
		        
		      </MDBRow>
		      <BannerMdbRow/>
		   </MDBContainer>
		</div >
		);
	}
}
export default Home;