package com.abhiroop.kubetime.cluster.restclient.http.svc;

import com.abhiroop.kubetime.cluster.restclient.http.pojo.ClusterClientBaseBuilder;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.txn.ProjectRequest;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.txn.ResourceQuotaRequest;

public interface IUserClusterDataService {

	String createNameSpace(ClusterClientBaseBuilder ccb, ProjectRequest pr);
	
	String delNameSpace(ClusterClientBaseBuilder ccb, String name);
	
	String addLabelToNamespace(ClusterClientBaseBuilder ccb , String namespaceName , String label);
	
	
	String createResourceQuota (ClusterClientBaseBuilder client , ResourceQuotaRequest rqo , String namespaceName);
}
