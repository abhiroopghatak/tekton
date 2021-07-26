import React, { Component } from 'react'
import { MDBContainer, MDBFooter,MDBAlert  } from "mdbreact";

class FooterComponent extends Component {
	constructor(props) {
		super(props)

		this.state = {

		}
	}

	render() {
		return (
			
		
				  
			<MDBFooter className="footer font-small pt-1 mt-4 bg-dark">
			
				<MDBContainer fluid className="text-center text-md-left">

				</MDBContainer>
				<div className="footer-copyright text-center py-1">
					<MDBContainer fluid className='text-white'>
						&copy; {new Date().getFullYear()} Copyright: <a href="https://www.hcl.com"> Abhiroop Ghatak </a>
					</MDBContainer>
				</div>
			</MDBFooter>
		)
	}
}

export default FooterComponent