package com.abhiroop.kubetime.cluster.restclient.http.svc;

import com.abhiroop.kubetime.cluster.restclient.http.pojo.ClusterClientBaseBuilder;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource.ClusterMetadata;

public interface IPlatformDataService {

	
	
	ClusterMetadata getPlatformSpec(ClusterClientBaseBuilder client);
}
