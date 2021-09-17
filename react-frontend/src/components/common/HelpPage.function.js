import React from 'react'
import { Link } from 'react-router-dom';
const HelpPage = () => {
	return (




		<div class="container-md z-index">
			<h3>Welcome to Kubetime <Link to="/home"><button className="btn-sm btn-dark float-right" > Go Back  </button></Link></h3>
			<hr />
			<div class="container">
				<div class="row">
					<div class="col"><div class="p-3 border bg-light">
						<h4 class="text-muted"> Account Sign Up</h4><hr />
						<p>This section elaborate user registration .
						Please <a href="/sign-up">click here</a> to land to sign up pages where you need to fill basic details such as full name , valid email address and password.
						OnSubmit form gets validated and logged a request . YOu can not sign in to system unless admin approves it . On Admin Approval you will recieve email on your registered email
						and based on status , you may proceed to login.
    </p>
					</div>
					</div>
					<div class="col">
						<div class="p-3 border bg-light">
							<h4 class="text-muted"> Account Sign in</h4><hr />
							<p>This section elaborate user Login .
						Please <a href="/login">click here</a> to land to login page where you need to fill registered email and password.
						You may login to the system only when you are registered and admin hass approved the same .
    						</p>

						</div>
					</div>
					<div class="col">
						<div class="p-3 border bg-light">
							<h4 class="text-muted"> Home Page</h4><hr />
							<p>This section elaborate user landing page i.e Home Page .
							On successful login you will navigated to home page where you see your basic user detail and on the right you will see the clusters in which you have access .
							If you need to raise new request for any cluster , you need to click the button below your profile with label 'Raise Access Request'.
    						</p>
						</div>
					</div>

				</div>
			</div>

			<hr />
			<div class="container">
				<div class="row">
					<div class="col"><div class="p-3 border bg-light">
						<h4 class="text-muted"> Profile Access</h4><hr />
						<p>On clicking left hamburger icon a nav menu list will slide from the left panel containing many useful Links. Clicking 'Profile' will land you to profile detail pages,
						where you can validate your details and wishing to change password there is a button for the same . You can put your old password and then new password to change your profile password.
    </p>
					</div>
					</div>
					<div class="col">
						<div class="p-3 border bg-light">
							<h4 class="text-muted"> Cluster Access Request</h4><hr />
							<p>On clicking left hamburger icon a nav menu list will slide from the left panel containing many useful Links. Clicking 'Cluster Access Request' will open a form, where you need to put your registered email ,
							select cluster from dropdown box and in label selector field need to provide the Label in KEY+VALUE format. Clicking submit will log a request on your behalf and Admin to take further action.you will be notified via email.
    						</p>

						</div>
					</div>
					<div class="col">
						<div class="p-3 border bg-light">
							<h4 class="text-muted">Cluster Resoure Usage</h4><hr />
							<p>On Homepage, under list of accessed cluster you will find a select button . Clicking the same will navigate you to resource utilization pages where you will find the cost details that admin has set for the cluster.Additinaly ,
							 in right hand view you can have atabular data of all namespaces and their resource data of which you have access . Clicking Export to file will lead you to print the data in pdf file. 
    						</p>
						</div>
					</div>

				</div>
			</div>
		</div>

	)

}

export default HelpPage;