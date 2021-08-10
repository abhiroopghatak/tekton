package com.abhiroop.kubetime.cluster.restclient.http.svc;

import java.util.List;

import org.json.simple.parser.ParseException;

import com.abhiroop.kubetime.cluster.restclient.http.pojo.ClusterClientBaseBuilder;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource.ClusterMetadata;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource.NamespaceResourceObject;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource.PodResourceObject;

public interface IPlatformDataService {

	
	
	ClusterMetadata getPlatformSpec(ClusterClientBaseBuilder client);
	
	
	List<String> getAccessibleNameSPaces(ClusterClientBaseBuilder client, String pamekey) throws Exception;
	
	
	NamespaceResourceObject getResourceQuota (ClusterClientBaseBuilder client, NamespaceResourceObject namespace) throws Exception;


	NamespaceResourceObject getVolumePerNamespace(ClusterClientBaseBuilder client, NamespaceResourceObject namespace)
			throws ParseException;
	
	
	List<PodResourceObject> getPodResourcePerNameSpace(ClusterClientBaseBuilder client , String namespace) throws Exception;
}
