package com.abhiroop.kubetime.cluster.restclient.http.pojo;

import com.abhiroop.kubetime.cluster.restclient.http.constants.openshift4.ClusterConstants;

public class OpenshiftClient extends ClusterClientBaseBuilder{

	
	
	@Override
	public
	String getClusterSpecApiUrl() {
		// TODO Auto-generated method stub
		return this.getBaseUrl()+ClusterConstants.RestApiRelativePath.CLUSTER_VERSIONS;
	}

	
	
	
}
