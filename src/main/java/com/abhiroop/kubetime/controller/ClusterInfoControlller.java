package com.abhiroop.kubetime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.abhiroop.kubetime.svc.ClusterInfoService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/clusters")
public class ClusterInfoControlller {

	
	@Autowired
	private ClusterInfoService clusterInfoService;
	
	@Autowired
	PlatformDataController pdc;
		
	
	@GetMapping("/health")
	public String health() {
		return "OK";
	}
	
	@GetMapping("/getOne/{id}")
	public Cluster getById(@PathVariable long id) {
		return clusterInfoService.getClusterById(id);
	}
	
	@GetMapping("/getAllByUser/{email}")
	public List<Cluster>  getAllByUserEmail(@PathVariable String userEmail) {
		return clusterInfoService.getClusterListOfUser(userEmail);
	}
	
	@GetMapping("/getAll")
	public List<Cluster>  getAll() {
		
		
		return clusterInfoService.getClusterLis();
	}
	
	
	@PostMapping("/clusterSummary")
	public ClusterMetadata  getClusterSummarty(@RequestBody ClusterMetadata cmd) {
		return pdc.getPlatformSpec(cmd);
	}
}
