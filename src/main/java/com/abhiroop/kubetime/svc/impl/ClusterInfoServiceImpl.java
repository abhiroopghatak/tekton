package com.abhiroop.kubetime.svc.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.abhiroop.kubetime.cluster.restclient.utils.DataFormatUtil;
import com.abhiroop.kubetime.pojo.Cluster;
import com.abhiroop.kubetime.repo.ClusterRepo;
import com.abhiroop.kubetime.svc.ClusterInfoService;

@Service
public class ClusterInfoServiceImpl implements ClusterInfoService {

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

	@Cacheable(value="clusters")           // it will cache result and key name will be "clusters"
	@Override
	public List<Cluster> getClusterList() {
		System.out.println("getClusterList : service call invoked");
		List<Cluster> cList = clusterRepo.findAll();
		System.out.println("All cluster retrieved from cionfig-DB.");
		for (Cluster cluster : cList) {
			try {
				cluster.setRegisteredon(DataFormatUtil.trimDateWithNoTime(cluster.getRegisteredon()));
				cluster.setToken("");
			} catch (ParseException pe) {
				System.out.println(pe);
				cluster.setErrorMessage("Date Parsing error occurred");
			}
		}
		
		return cList;
	}

	@Override
	public Cluster addCluster(Cluster c) throws Exception {
		
		
		c= clusterRepo.save(c);
		
		return c;
	}

	

}
