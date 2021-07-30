import React from 'react';

import { MDBRow, MDBCol, MDBCardTitle } from "mdbreact";

function BannerMdbRow() {
	return (
		<MDBRow>
			<MDBCol sm="12">
				<MDBCol className="text-black text-center py-5 px-4 my-5" style={{ backgroundImage: "url(https://mdbcdn.b-cdn.net/img/Photos/Others/gradient1.jpg)" }}>
					<MDBCol className="py-9">
						<MDBCardTitle className="h1-responsive m-5 font-bold">Verify kubernetes cluster usage and reduce cluster spending</MDBCardTitle>
						<p className="mx-5 mb-5 ">Kubetime provides realtime kubernetes cluster usage and generates time based spending . Adding to that it can facilitate as advisor which can suggest house keeping opertaion to recduce cluster spending.
		               </p>
					</MDBCol>
				</MDBCol>
			</MDBCol>
		</MDBRow>
	);
};
export default BannerMdbRow;