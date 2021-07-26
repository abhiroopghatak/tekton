package com.abhiroop.kubetime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhiroop.kubetime.pojo.Cluster;
import com.abhiroop.kubetime.svc.ClusterDataService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/clusters")
public class ClusterDataControlller {

	
	@Autowired
	private ClusterDataService clusterDataService;
	
	@GetMapping("/health")
	public String health() {
		return "OK";
	}
	
	@GetMapping("/getOne/{id}")
	public Cluster getById(@PathVariable long id) {
		return clusterDataService.getClusterById(id);
	}
	
	@GetMapping("/getAllByUser/{email}")
	public List<Cluster>  getAllByUserEmail(@PathVariable String userEmail) {
		return clusterDataService.getClusterListOfUser(userEmail);
	}
	
	@GetMapping("/getAll")
	public List<Cluster>  getAll() {
		return clusterDataService.getClusterLis();
	}
}
