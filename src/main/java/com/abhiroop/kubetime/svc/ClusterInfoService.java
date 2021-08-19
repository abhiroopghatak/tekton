package com.abhiroop.kubetime.svc;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhiroop.kubetime.pojo.Cluster;

@Service
public interface ClusterInfoService {

	
	
	Cluster getClusterById(long uuid);
	List<Cluster> getClusterListOfUser(String userEmail);
	
	Cluster  disableCluster(Cluster c);
	List<Cluster> getClusterList();
	
	
	Cluster addCluster(Cluster c)throws Exception;
	
}
