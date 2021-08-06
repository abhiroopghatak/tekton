package com.abhiroop.kubetime.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhiroop.kubetime.cluster.restclient.http.PlatformDataController;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource.ClusterMetadata;
import com.abhiroop.kubetime.pojo.Cluster;
import com.abhiroop.kubetime.pojo.ItemCost;
import com.abhiroop.kubetime.svc.ClusterInfoService;
import com.abhiroop.kubetime.svc.ICostService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/clusters")
public class ClusterInfoControlller {

	@Autowired
	private ClusterInfoService clusterInfoService;

	@Autowired
	private ICostService costService;

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	PlatformDataController pdc;

	@GetMapping("/health")
	public String health() {
		return "OK";
	}

	// clear all cache using cache manager
	@RequestMapping(value = "/clearCache")
	public String clearCache() {
		for (String name : cacheManager.getCacheNames()) {
			cacheManager.getCache(name).clear();
		}
		System.out.println("cache flushed successfully");
		return "cache flushed successfully";
	}

	@GetMapping("/getOne/{id}")
	public Cluster getById(@PathVariable String id) {

		return clusterInfoService.getClusterById(Long.parseLong(id));
	}

	@GetMapping("/getAllByUser/{email}")
	public List<Cluster> getAllByUserEmail(@PathVariable String userEmail) {
		return clusterInfoService.getClusterListOfUser(userEmail);
	}

	@GetMapping("/getAll")
	public List<Cluster> getAll() {

		return clusterInfoService.getClusterList();
	}

	@PostMapping("/clusterSummary")
	public ClusterMetadata getClusterSummarty(@RequestBody ClusterMetadata cmd) {
		Cluster c = getById(cmd.getClusterId());
		cmd.setEndPointUrl(c.getEndpoint());
		cmd.setToken(c.getToken());
		cmd = pdc.getPlatformSpec(cmd);
		cmd.setClusterName(c.getName());
		cmd.setEnv(c.getEnvironment());
		return cmd;
	}

	/////////////////////// cost details//////////////

	@GetMapping("/getcosts/{id}")
	public ItemCost getAll(@PathVariable String id) {
		ItemCost ic = new ItemCost();
		long cid = 0;
		try {
			cid = Long.parseLong(id);
			ic = costService.getCostDeatilPerCluster(cid);
			System.out.println("cost per cluster data recieved");
		} catch (RuntimeException pe) {
			System.out.println(pe);
		}
		return ic;
	}

	/////////////////// cost details//////////////
}
