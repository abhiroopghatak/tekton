import React, { useRef } from "react";
import { useLocation } from "react-router-dom";
import ReactToPrint from "react-to-print";
import ResourceUsagePrintClass from "./ResourceUsagePrintClass.js";

const ClusterResourceUsagePrint =()=> {

const componentRef = useRef();
const location = useLocation();

const   data=location.state.data;
const  costData = location.state.costData;
const cluster = location.state.cluster;

		return (
			<div>
				<ReactToPrint
				
					trigger={() => <button className="fa fa-print">Print this out!</button>}
					content={() => componentRef.current}
				/>
				<hr/>
				<ResourceUsagePrintClass ref={componentRef} data = {data} costData={costData} cluster={cluster}/>
			</div>
		);
}






export default ClusterResourceUsagePrint;