package com.abhiroop.kubetime.svc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhiroop.kubetime.pojo.Cluster;
import com.abhiroop.kubetime.repo.ClusterRepo;


@Service
public class ClusterDataServiceImpl implements ClusterDataService {

	@Autowired
	ClusterRepo clusterRepo;

	@Override
	public Cluster getClusterById(long uuid) {
		Cluster clusterObj = clusterRepo.getById(uuid);
		return clusterObj;
	}

	@Override
	public List<Cluster> getClusterListOfUser(String userEmail) {
		List<Long> ids = new ArrayList<Long>();
		List<Cluster> cList = clusterRepo.findAllById(ids);
		return cList;
	}

	@Override
	public Cluster disableCluster(Cluster c) {
		// TODO Auto-generated method stub
		c.setStatus("I");
		return clusterRepo.saveAndFlush(c);
	}

	@Override
	public List<Cluster> getClusterLis() {
		// TODO Auto-generated method stub
		return  clusterRepo.findAll();
				
	}
	
	
	

}
